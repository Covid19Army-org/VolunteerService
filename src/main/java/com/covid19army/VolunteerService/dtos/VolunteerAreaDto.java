package com.covid19army.VolunteerService.dtos;


public class VolunteerAreaDto {

	private long volunteerareaid;
	
	private String availabilityPincodes;
	
	private String district;

	private String state;

	public long getVolunteerareaid() {
		return volunteerareaid;
	}

	public void setVolunteerareaid(long volunteerareaid) {
		this.volunteerareaid = volunteerareaid;
	}

	public String getAvailabilityPincodes() {
		return availabilityPincodes;
	}

	public void setAvailabilityPincodes(String availabilityPincodes) {
		this.availabilityPincodes = availabilityPincodes;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
