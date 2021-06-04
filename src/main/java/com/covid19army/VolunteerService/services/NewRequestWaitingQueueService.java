package com.covid19army.VolunteerService.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.repositories.NewRequestWaitingQueueRepository;
import com.covid19army.VolunteerService.repositories.VolunteerRepository;
import com.covid19army.core.dtos.RequestVolutneerStatusDto;
import com.covid19army.core.exceptions.ResourceNotFoundException;

@Service
public class NewRequestWaitingQueueService {

	@Autowired
	NewRequestWaitingQueueRepository _newRequestWaitingQueueRepository;
	
	@Autowired
	VolunteerRepository _volunteerRepository;
	
	public long createQueueItem(long requestId, long volunteerId) {
		NewRequestWaitingQueue newModel = new NewRequestWaitingQueue();
		newModel.setRequestid(requestId);
		newModel.setVolunteerid(volunteerId);
		newModel.setDateCreated(new Date());
		
		_newRequestWaitingQueueRepository.save(newModel);
		
		return newModel.getItemid();
	}
		
	public void processRequestVolunteerStatus(RequestVolutneerStatusDto dto) {
	
		if(dto.isIsaccepted()) {
			processAccept(dto.getRequestid(), dto.getVolunteerid());
		}else {
			processReject(dto.getRequestid(), dto.getVolunteerid());
		}
	}
	
	
	private void processAccept(long requestid, long volunteerid) {
		var optionalVolunteer = _volunteerRepository.findById(volunteerid);
		if(optionalVolunteer.isPresent()) {
			_newRequestWaitingQueueRepository.deleteByRequestid(requestid);
		}
	}	
	
	private void processReject(long requestid, long volunteerid) {
		_newRequestWaitingQueueRepository.deleteByRequestidAndVolunteerid(requestid, volunteerid);
	}
}
