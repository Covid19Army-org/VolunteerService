package com.covid19army.VolunteerService.dtos;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.covid19army.core.enums.NeedsEnum;


public class VolunteerResponseDto extends VolunteerDto {
	
	private int countrycode;
	private boolean isactive;

	private boolean iscontactverified;
	
	private Date dateCreated;
	private Date dateUpdated;
	private List<VolunteerProvidedNeedDto> volunteerprovidedneeds;
	public int getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isIscontactverified() {
		return iscontactverified;
	}
	public void setIscontactverified(boolean iscontactverified) {
		this.iscontactverified = iscontactverified;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public List<VolunteerProvidedNeedDto> getVolunteerprovidedneeds() {
		return volunteerprovidedneeds;
	}
	public void setVolunteerprovidedneeds(List<VolunteerProvidedNeedDto> volunteerprovidedneeds) {
		this.volunteerprovidedneeds = volunteerprovidedneeds;
		List<NeedsEnum> needs = volunteerprovidedneeds.stream()
				.map(VolunteerProvidedNeedDto::getNeedid)
				.collect(Collectors.toList());
		this.setNeeds(needs);
	}
	
	
	
}
