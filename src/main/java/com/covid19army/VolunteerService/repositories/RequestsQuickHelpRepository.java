package com.covid19army.VolunteerService.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.covid19army.VolunteerService.models.RequestsQuickHelp;

public interface RequestsQuickHelpRepository extends PagingAndSortingRepository<RequestsQuickHelp, String>{

	List<RequestsQuickHelp> findByRequestownerid(long requestownerid);
}
