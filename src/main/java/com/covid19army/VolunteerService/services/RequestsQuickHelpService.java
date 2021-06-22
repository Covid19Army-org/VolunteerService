package com.covid19army.VolunteerService.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.dtos.QuickHelpDto;
import com.covid19army.VolunteerService.models.QuickHelp;
import com.covid19army.VolunteerService.models.QuickHelpNeed;
import com.covid19army.VolunteerService.repositories.QuickHelpRepository;
import com.covid19army.VolunteerService.repositories.RequestsQuickHelpRepository;
import com.covid19army.core.enums.NeedsEnum;
import com.covid19army.core.exceptions.NotAuthorizedException;
import com.covid19army.core.exceptions.ResourceNotFoundException;
import com.covid19army.core.extensions.HttpServletRequestExtension;

@Service
public class RequestsQuickHelpService {

	@Autowired
	RequestsQuickHelpRepository _requestQuickHelpRepository;
	
	@Autowired
	HttpServletRequestExtension _requestExtension;
	
	@Autowired
	ModelMapper _mapper;
	
	@Autowired
	VolunteerService _volunteerService;
	
	@Autowired
	QuickHelpRepository _quickHelpRepository;
	
	public List<QuickHelpDto> getQuickHelpsForUser(){
		var authUserId = Long.parseLong(_requestExtension.getAuthenticatedUser());
		var result = _requestQuickHelpRepository.findByRequestownerid(authUserId);
		var resultDto = result.stream().map(q-> _mapper.map(q, QuickHelpDto.class))
				.collect(Collectors.toList());
		
		return resultDto;
	}

	public long createQuickHelp(QuickHelpDto quickhelp) throws ResourceNotFoundException, NotAuthorizedException {
		// TODO Auto-generated method stub
		var authUserId = Long.parseLong(_requestExtension.getAuthenticatedUser());
		var quickhelpmodel = _mapper.map(quickhelp, QuickHelp.class);
		quickhelpmodel.setDateCreated(new Date());
		quickhelpmodel.setUserid(authUserId);
		
		var volunteerOpt = _volunteerService.getVolunteerById(quickhelpmodel.getVolunteerid());
		if(volunteerOpt.isEmpty())
			throw new ResourceNotFoundException("Invalid Volunteer.");
		
		var volunteer = volunteerOpt.get();
		if(volunteer.getUserid() != authUserId)
			throw new NotAuthorizedException("You are not authorized to do this.");
		
		//TODO set needs
		List<QuickHelpNeed> needs = new ArrayList<>();
		for(NeedsEnum need : quickhelp.getNeeds()) {
			needs.add(new QuickHelpNeed(need, quickhelpmodel));
		}
		quickhelpmodel.setQuickhelpneeds(needs);
		_quickHelpRepository.save(quickhelpmodel);		
		return quickhelpmodel.getQuickhelpid();
	}

	public void deleteQuickHelp(long quickhelpid) throws ResourceNotFoundException, NotAuthorizedException {
		// TODO Auto-generated method stub
		var authUserId = Long.parseLong(_requestExtension.getAuthenticatedUser());
		var quickhelpOpt = _quickHelpRepository.findById(quickhelpid);
		if(quickhelpOpt.isEmpty())
			throw new ResourceNotFoundException("Quick hlp not found");
		
		var quickhelp = quickhelpOpt.get();
		if(quickhelp.getUserid() != authUserId)
			throw new NotAuthorizedException("You are not authorized to delete.");
		
		_quickHelpRepository.delete(quickhelp);
	}
	
}
