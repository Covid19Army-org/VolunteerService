package com.covid19army.VolunteerService.modelListeners;

import java.util.Date;

import javax.persistence.PrePersist;

import com.covid19army.VolunteerService.models.VolunteerArea;

public class VolunteerAreaModelListener {

	@PrePersist
	public void onCreating(VolunteerArea volunteerArea) {
		Date currentDate = new Date();
		volunteerArea.setDateCreated(currentDate);
		volunteerArea.setDateUpdated(currentDate);
	}
}
