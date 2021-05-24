package com.covid19army.VolunteerService.repositories;

import org.springframework.data.repository.CrudRepository;

import com.covid19army.VolunteerService.models.Volunteer;

public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

}
