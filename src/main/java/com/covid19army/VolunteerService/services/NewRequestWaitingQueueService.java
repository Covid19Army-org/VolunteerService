package com.covid19army.VolunteerService.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.repositories.NewRequestWaitingQueueRepository;
import com.covid19army.VolunteerService.repositories.VolunteerRepository;
import com.covid19army.core.constants.ActivityTypeConstant;
import com.covid19army.core.constants.EntityTypeConstant;
import com.covid19army.core.dtos.ActivityLogDto;
import com.covid19army.core.dtos.RequestVolutneerStatusDto;
import com.covid19army.core.exceptions.ResourceNotFoundException;
import com.covid19army.core.mex.rabbitmq.RabbitMQSender;
import com.covid19army.core.utilities.Helper;

@Service
public class NewRequestWaitingQueueService {

	@Autowired
	NewRequestWaitingQueueRepository _newRequestWaitingQueueRepository;
	
	@Autowired
	VolunteerRepository _volunteerRepository;
	
	@Autowired
	@Qualifier("activitylogExchangeSender")
	RabbitMQSender _activitylogExchangeSender;
	
	public long createQueueItem(long requestId, long volunteerId, long requestownerId) {
		NewRequestWaitingQueue newModel = new NewRequestWaitingQueue();
		newModel.setRequestid(requestId);
		newModel.setVolunteerid(volunteerId);
		newModel.setDateCreated(new Date());
		
		_newRequestWaitingQueueRepository.save(newModel);
		
		this.publishActivity(requestId, EntityTypeConstant.HELP_REQUEST,
				ActivityTypeConstant.CREATE, requestownerId, volunteerId);
		
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
	
	protected void publishActivity(long entityid, String entity, String action, long fromuserid, long touserid) {
		var dto = Helper.createActivityLogDto(entityid, entity, action, fromuserid, touserid);
		_activitylogExchangeSender.<ActivityLogDto>send(dto);
	}
}
