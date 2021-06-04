package com.covid19army.VolunteerService.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.models.RequestVolunteer;

@Transactional
public interface NewRequestWaitingQueueRepository extends PagingAndSortingRepository<NewRequestWaitingQueue, Long> {

	void deleteByRequestid(long requestid);
	void deleteByRequestidAndVolunteerid(long requestid, long volunteerid);
}
