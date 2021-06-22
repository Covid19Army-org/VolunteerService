package com.covid19army.VolunteerService.mex.consumers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.covid19army.VolunteerService.dtos.HelpRequestDto;
import com.covid19army.VolunteerService.dtos.VolunteerSearchDto;
import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.services.NewRequestWaitingQueueService;
import com.covid19army.VolunteerService.services.VolunteerService;
import com.covid19army.core.dtos.PagedResponseDto;
import com.covid19army.core.dtos.RequestVolutneerStatusDto;


@Component
public class RabbitMQConsumer {
	
	@Autowired
	VolunteerService _volunteerService;
	
	@Autowired
	NewRequestWaitingQueueService _newRequestWaitingQueue;
	
	@Autowired
	ModelMapper _mapper;

	@RabbitListener(queues = "${covid19army.rabbitmq.newhelprequestwaitingqueue}")
	public void recievedMessage(HelpRequestDto message) {		
		System.out.println("Recieved Message From RabbitMQ: " + message.getRequestid());
				
		Pageable pageable = PageRequest.of(0, 500,Sort.by(Sort.Direction.DESC, "dateCreated"));
		VolunteerSearchDto searchDto = _mapper.map(message, VolunteerSearchDto.class);
		PagedResponseDto<Long> voluteerPage = _volunteerService.getVolunteerIdsBasedOnAreaAndNeed(searchDto, pageable);
		for(long volunteerId : voluteerPage.getData()) {
			_newRequestWaitingQueue.createQueueItem(message.getRequestid(), volunteerId, message.getUserid());
		}
	}
	
	@RabbitListener(queues = "${covid19army.rabbitmq.newrequestacceptrejectqueue}")
	public void requestVolunteerStatusMessageListener(RequestVolutneerStatusDto message) {
		_newRequestWaitingQueue.processRequestVolunteerStatus(message);
	}
}
