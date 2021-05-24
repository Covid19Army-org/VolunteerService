package com.covid19army.VolunteerService.dtos;

import java.util.List;

import com.covid19army.core.enums.NeedsEnum;

public class VolunteerDto {

	private long volunteerid;

	private String contactname;

	private String contactnumber;

	private int countrycode;
	
	private String district;
	
	private String name;

	private String pincode;

	private String state;

	private int type;
	
	private List<NeedsEnum> needs;
	
	private List<VolunteerAreaDto> volunteerareas;

	public long getVolunteerid() {
		return volunteerid;
	}

	public void setVolunteerid(long volunteerid) {
		this.volunteerid = volunteerid;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<NeedsEnum> getNeeds() {
		return needs;
	}

	public void setNeeds(List<NeedsEnum> needs) {
		this.needs = needs;
	}

	public List<VolunteerAreaDto> getAreas() {
		return volunteerareas;
	}

	public void setAreas(List<VolunteerAreaDto> volunteerareas) {
		this.volunteerareas = volunteerareas;
	}
	
	
}
