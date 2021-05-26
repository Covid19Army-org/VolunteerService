package com.covid19army.VolunteerService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19army.VolunteerService.dtos.VolunteerDto;
import com.covid19army.VolunteerService.dtos.VolunteerResponseDto;
import com.covid19army.VolunteerService.dtos.VolunteerSearchDto;
import com.covid19army.VolunteerService.services.VolunteerService;
import com.covid19army.core.dtos.PagedResponseDto;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

	@Autowired
	VolunteerService _volunteerService;
	
	@PostMapping
	public long createVolunteer(@RequestBody VolunteerDto volunteerDto) {
		return _volunteerService.createVolunteer(volunteerDto);		
	}
	
	@PostMapping("/search")
	public PagedResponseDto<VolunteerResponseDto> searchVolunteer(@RequestBody VolunteerSearchDto searchDto, 
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "50") int size ) {
		Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC, "dateCreated"));
		return _volunteerService.getVolunteersBasedOnArea(searchDto, pageable);		
	}
	
	@PostMapping("/searchid")
	public PagedResponseDto<Long> searchVolunteerId(@RequestBody VolunteerSearchDto searchDto, 
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "100") int size ) {
		Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC, "dateCreated"));
		return _volunteerService.getVolunteerIdsBasedOnArea(searchDto, pageable);		
	}
}
