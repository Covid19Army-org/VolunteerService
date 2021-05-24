package com.covid19army.VolunteerService.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the volunteerareas database table.
 * 
 */
@Entity
@Table(name="volunteerareas")
@NamedQuery(name="VolunteerArea.findAll", query="SELECT v FROM VolunteerArea v")
public class VolunteerArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long volunteerareaid;

	@Column(name="availability_pincodes")
	private String availabilityPincodes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_updated")
	private Date dateUpdated;

	private String district;

	private String state;

	//bi-directional many-to-one association to Volunteer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="volunteerid")
	private Volunteer volunteer;

	public VolunteerArea() {
	}

	public long getVolunteerareaid() {
		return this.volunteerareaid;
	}

	public void setVolunteerareaid(long volunteerareaid) {
		this.volunteerareaid = volunteerareaid;
	}

	public String getAvailabilityPincodes() {
		return this.availabilityPincodes;
	}

	public void setAvailabilityPincodes(String availabilityPincodes) {
		this.availabilityPincodes = availabilityPincodes;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Volunteer getVolunteer() {
		return this.volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

}