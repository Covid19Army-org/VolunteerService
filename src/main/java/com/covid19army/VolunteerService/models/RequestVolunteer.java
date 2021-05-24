package com.covid19army.VolunteerService.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the requestvolunteer database table.
 * 
 */
@Entity
@NamedQuery(name="RequestVolunteer.findAll", query="SELECT r FROM RequestVolunteer r")
public class RequestVolunteer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long requestvolunteerid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_message_last_seen_by_volunteer")
	private Date dateMessageLastSeenByVolunteer;

	private byte isactive;

	private byte isrejected;

	private String rejectreason;

	private long requestid;

	//bi-directional many-to-one association to Volunteer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="volunteerid")
	private Volunteer volunteer;

	public RequestVolunteer() {
	}

	public long getRequestvolunteerid() {
		return this.requestvolunteerid;
	}

	public void setRequestvolunteerid(long requestvolunteerid) {
		this.requestvolunteerid = requestvolunteerid;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateMessageLastSeenByVolunteer() {
		return this.dateMessageLastSeenByVolunteer;
	}

	public void setDateMessageLastSeenByVolunteer(Date dateMessageLastSeenByVolunteer) {
		this.dateMessageLastSeenByVolunteer = dateMessageLastSeenByVolunteer;
	}

	public byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public byte getIsrejected() {
		return this.isrejected;
	}

	public void setIsrejected(byte isrejected) {
		this.isrejected = isrejected;
	}

	public String getRejectreason() {
		return this.rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public long getHelprequest() {
		return this.requestid;
	}

	public void setHelprequest(long requestid) {
		this.requestid = requestid;
	}

	public Volunteer getVolunteer() {
		return this.volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

}