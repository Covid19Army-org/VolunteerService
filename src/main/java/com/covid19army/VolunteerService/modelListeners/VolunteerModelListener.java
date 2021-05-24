package com.covid19army.VolunteerService.modelListeners;

import java.util.Date;

import javax.persistence.PrePersist;

import com.covid19army.VolunteerService.models.Volunteer;

public class VolunteerModelListener {

	@PrePersist
	public void onCreating(Volunteer volunteer) {
		Date currentDate = new Date();
		volunteer.setCountrycode(91);
		volunteer.setDateCreated(currentDate);
		volunteer.setDateUpdated(currentDate);
		volunteer.setIsactive(true);
		volunteer.setIscontactverified(false);
		volunteer.setIsdelete(true);		
	}
}
