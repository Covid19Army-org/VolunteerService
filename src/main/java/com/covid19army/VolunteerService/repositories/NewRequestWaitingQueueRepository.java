package com.covid19army.VolunteerService.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.covid19army.VolunteerService.models.NewRequestWaitingQueue;
import com.covid19army.VolunteerService.models.RequestVolunteer;

public interface NewRequestWaitingQueueRepository extends PagingAndSortingRepository<NewRequestWaitingQueue, Long> {

}
