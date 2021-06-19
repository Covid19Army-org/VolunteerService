package com.covid19army.VolunteerService.models;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="quickhelp")
public class QuickHelp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quickhelpid;
	
	private long userid;
	
	private long volunteerid;
	
	private String state;
	
	private String district;
	
	@Column(name="availability_pincodes")
	private String availabilityPincodes;
	
	private String comments;
	
	@OneToMany(mappedBy = "quickhelp", cascade = CascadeType.ALL)
	private List<QuickHelpNeed> quickhelpneeds;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	public long getQuickhelpid() {
		return quickhelpid;
	}

	public void setQuickhelpid(long quickhelpid) {
		this.quickhelpid = quickhelpid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getVolunteerid() {
		return volunteerid;
	}

	public void setVolunteerid(long volunteerid) {
		this.volunteerid = volunteerid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAvailabilityPincodes() {
		return availabilityPincodes;
	}

	public void setAvailabilityPincodes(String availabilityPincodes) {
		this.availabilityPincodes = availabilityPincodes;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<QuickHelpNeed> getQuickhelpneeds() {
		return quickhelpneeds;
	}

	public void setQuickhelpneeds(List<QuickHelpNeed> quickhelpneeds) {
		this.quickhelpneeds = quickhelpneeds;
	}
	
	
	
}
