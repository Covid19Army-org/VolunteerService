package com.covid19army.VolunteerService.controllers;

import java.util.List;

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
import com.covid19army.core.common.clients.OtpServiceClient;
import com.covid19army.core.dtos.OtpVerificationRequestDto;
import com.covid19army.core.dtos.PagedResponseDto;
import com.covid19army.core.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

	@Autowired
	VolunteerService _volunteerService;
	
	@Autowired
	OtpServiceClient _otpServiceClient;
	
	@PostMapping
	public long createVolunteer(@RequestBody VolunteerDto volunteerDto) {
		return _volunteerService.createVolunteer(volunteerDto);		
	}
	
	@PostMapping("/searchbyids")
	public List<VolunteerResponseDto> getVolunteerByIds(@RequestBody List<Long> volunteerIds){
		return _volunteerService.getVolunteerByIds(volunteerIds);
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
	
	@PostMapping("/validateotp")
	public boolean validateOtp(@RequestBody OtpVerificationRequestDto otpRequestDto) 
			throws ResourceNotFoundException{
		
		var result = _otpServiceClient.validateOtp(otpRequestDto);
		if(result) {
			_volunteerService.updateMobileVerified(otpRequestDto.getEntityid(), true);
		}
		
		throw new ResourceNotFoundException("Invaid Otp.");
		
	}
}
