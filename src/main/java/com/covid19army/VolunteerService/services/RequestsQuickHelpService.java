package com.covid19army.VolunteerService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.dtos.QuickHelpDto;
import com.covid19army.VolunteerService.repositories.RequestsQuickHelpRepository;
import com.covid19army.core.extensions.HttpServletRequestExtension;

@Service
public class RequestsQuickHelpService {

	@Autowired
	RequestsQuickHelpRepository _requestQuickHelpRepository;
	
	@Autowired
	HttpServletRequestExtension _requestExtension;
	
	@Autowired
	ModelMapper _mapper;
	
	public List<QuickHelpDto> getQuickHelpsForUser(){
		var authUserId = Long.parseLong(_requestExtension.getAuthenticatedUser());
		var result = _requestQuickHelpRepository.findByRequestownerid(authUserId);
		var resultDto = result.stream().map(q-> _mapper.map(q, QuickHelpDto.class))
				.collect(Collectors.toList());
		
		return resultDto;
	}
	
}
