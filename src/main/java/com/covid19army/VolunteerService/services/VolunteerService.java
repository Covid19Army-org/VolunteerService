package com.covid19army.VolunteerService.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.dtos.VolunteerDto;
import com.covid19army.VolunteerService.models.Volunteer;
import com.covid19army.VolunteerService.models.VolunteerProvidedNeed;
import com.covid19army.VolunteerService.repositories.VolunteerRepository;
import com.covid19army.core.enums.NeedsEnum;
import com.covid19army.core.extensions.HttpServletRequestExtension;

@Service
public class VolunteerService {

	@Autowired
	HttpServletRequestExtension _httpHttpServletRequestExtension;
	
	@Autowired
	ModelMapper _mapper;
	
	@Autowired
	VolunteerRepository _volunteerRepository;
	
	public long createVolunteer(VolunteerDto dto) {
		
		Volunteer volunteerModel = _mapper.map(dto, Volunteer.class);
		List<VolunteerProvidedNeed> needsList = new ArrayList<>();
		for(NeedsEnum needEnum : dto.getNeeds()) {
			needsList.add(new VolunteerProvidedNeed(needEnum,volunteerModel));
		}
		
		volunteerModel.setVolunteerprovidedneeds(needsList);
		
		return volunteerModel.getVolunteerid();
	}
}
