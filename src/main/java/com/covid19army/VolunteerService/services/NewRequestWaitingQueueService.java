package com.covid19army.VolunteerService.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.repositories.NewRequestWaitingQueueRepository;

@Service
public class NewRequestWaitingQueueService {

	@Autowired
	NewRequestWaitingQueueRepository _newRequestWaitingQueueRepository;
	
	public long createQueueItem(long requestId, long volunteerId) {
		NewRequestWaitingQueue newModel = new NewRequestWaitingQueue();
		newModel.setRequestid(requestId);
		newModel.setVolunteerid(volunteerId);
		newModel.setDateCreated(new Date());
		
		_newRequestWaitingQueueRepository.save(newModel);
		
		return newModel.getItemid();
	}
}
