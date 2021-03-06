package com.covid19army.VolunteerService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.dtos.VolunteerDto;
import com.covid19army.VolunteerService.dtos.VolunteerResponseDto;
import com.covid19army.VolunteerService.dtos.VolunteerSearchDto;
import com.covid19army.VolunteerService.models.Volunteer;
import com.covid19army.VolunteerService.models.VolunteerArea;
import com.covid19army.VolunteerService.models.VolunteerProvidedNeed;
import com.covid19army.VolunteerService.repositories.VolunteerRepository;
import com.covid19army.core.dtos.MobileVerificationQueueDto;
import com.covid19army.core.dtos.PagedResponseDto;
import com.covid19army.core.enums.NeedsEnum;
import com.covid19army.core.exceptions.ResourceNotFoundException;
import com.covid19army.core.extensions.HttpServletRequestExtension;
import com.covid19army.core.mex.rabbitmq.RabbitMQSender;

@Service
public class VolunteerService {

	@Autowired
	HttpServletRequestExtension _httpServletRequestExtension;
	
	@Autowired
	ModelMapper _mapper;
	
	@Autowired
	VolunteerRepository _volunteerRepository;
	
	@Autowired
	@Qualifier("otpExchangeSender")
	RabbitMQSender _otpExchangeSender;
	
	public Optional<Volunteer> getVolunteerById(long volunteerid) {
		return  _volunteerRepository.findById(volunteerid);
	}
	
	public long createVolunteer(VolunteerDto dto) {
		
		Volunteer volunteerModel = _mapper.map(dto, Volunteer.class);
		long userid = Long.parseLong(_httpServletRequestExtension.getAuthenticatedUser());
		volunteerModel.setUserid(userid);
		List<VolunteerProvidedNeed> needsList = new ArrayList<>();
		for(NeedsEnum needEnum : dto.getNeeds()) {
			needsList.add(new VolunteerProvidedNeed(needEnum,volunteerModel));
		}	
		
		List<VolunteerArea> areas = volunteerModel.getVolunteerareas()
		.stream()
		.map(va -> updateVolunter(va, volunteerModel))
		.collect(Collectors.toList());
		
		volunteerModel.setVolunteerareas(areas);
		volunteerModel.setVolunteerprovidedneeds(needsList);
		_volunteerRepository.save(volunteerModel);
		
		MobileVerificationQueueDto otpdto = new MobileVerificationQueueDto();
		otpdto.setMobilenumber(volunteerModel.getContactnumber());
		otpdto.setEntityid(volunteerModel.getVolunteerid());
		otpdto.setEntitytype("VTR");
		_otpExchangeSender.<MobileVerificationQueueDto>send(otpdto);
		
		return volunteerModel.getVolunteerid();
	}
	
	public PagedResponseDto<Long> getVolunteerIdsBasedOnArea(VolunteerSearchDto searchDto, Pageable pageable){
		Page<Long> volunteerIdPage = _volunteerRepository.findVolunteerIdByVolunteerArea(searchDto.getState(),
				searchDto.getDistrict(), searchDto.getPincode(), pageable);
		PagedResponseDto<Long> responseDto = new PagedResponseDto<>();
		responseDto.setCurrentPage(volunteerIdPage.getNumber());
		responseDto.setTotalItems(volunteerIdPage.getTotalElements());
		responseDto.setTotalPages(volunteerIdPage.getTotalPages());
		responseDto.setData(volunteerIdPage.getContent());
		return responseDto;
	}
	
	public PagedResponseDto<VolunteerResponseDto> getVolunteersBasedOnArea(VolunteerSearchDto searchDto, Pageable pageable){
		Page<Volunteer> volunteerPage = _volunteerRepository.findByVolunteerArea(searchDto.getState(),
				searchDto.getDistrict(), searchDto.getPincode(), pageable);
		
		List<VolunteerResponseDto> volunteerResponseDtoList = volunteerPage.getContent().stream()
				.map(v->_mapper.map(v, VolunteerResponseDto.class))
				.collect(Collectors.toList());
		
		PagedResponseDto<VolunteerResponseDto> responseDto = new PagedResponseDto<>();
		responseDto.setCurrentPage(volunteerPage.getNumber());
		responseDto.setTotalItems(volunteerPage.getTotalElements());
		responseDto.setTotalPages(volunteerPage.getTotalPages());
		responseDto.setData(volunteerResponseDtoList);
		return responseDto;
	}
	
	public PagedResponseDto<Long> getVolunteerIdsBasedOnAreaAndNeed(VolunteerSearchDto searchDto, Pageable pageable){
		Page<Long> volunteerPage = _volunteerRepository.findVolunteerIdByVolunteerAreaAndNeeds(searchDto.getState(),
				searchDto.getDistrict(), searchDto.getPincode(), searchDto.getNeeds(), pageable);
		
		
		
		PagedResponseDto<Long> responseDto = new PagedResponseDto<>();
		responseDto.setCurrentPage(volunteerPage.getNumber());
		responseDto.setTotalItems(volunteerPage.getTotalElements());
		responseDto.setTotalPages(volunteerPage.getTotalPages());
		responseDto.setData(volunteerPage.getContent());
		return responseDto;
	}
	
	public List<VolunteerResponseDto> getVolunteerByIds(List<Long> vounteerIds){
		Iterable<Volunteer> volunteerList =   _volunteerRepository.findAllById(vounteerIds);
		var volunteerDtoList = StreamSupport.stream(volunteerList.spliterator(), false)
		.map(v-> _mapper.map(v, VolunteerResponseDto.class))
		.collect(Collectors.toList());
		
		return volunteerDtoList;
	}
	
	public void updateMobileVerified(long requestId, boolean isVerified) throws ResourceNotFoundException {
		Optional<Volunteer> helpRequest =  _volunteerRepository.findById(requestId);
		if(helpRequest.isEmpty()) {

			throw new ResourceNotFoundException("Invalid volunteer Id");
		}
		var model = helpRequest.get();
		model.setIscontactverified(isVerified);
		_volunteerRepository.save(model);
		
	}
	
	private VolunteerArea updateVolunter(VolunteerArea volunteerArea, Volunteer volunteer) {
		volunteerArea.setVolunteer(volunteer);
		return volunteerArea;
	}
}
