package com.covid19army.VolunteerService.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.covid19army.core.enums.NeedsEnum;
import com.google.errorprone.annotations.Immutable;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity
@Immutable
@Table(name="vw_requests_quickhelp")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Subselect("select uuid() as id, rqh.* from vw_requests_quickhelp rqh")
public class RequestsQuickHelp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private long quickhelpid;
	
	private long requestownerid;
	
	private long volunteeruserid;
	
	private String volunteername;
	
	private String state;
	
	private String district;
	
	@Column(name="availability_pincodes")
	private String availabilityPincodes;
	
	@Type( type = "json" )
	@Column( columnDefinition = "json" )
	private List<NeedsEnum> needs;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	private String comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getQuickhelpid() {
		return quickhelpid;
	}

	public void setQuickhelpid(long quickhelpid) {
		this.quickhelpid = quickhelpid;
	}

	public long getRequestownerid() {
		return requestownerid;
	}

	public void setRequestownerid(long requestownerid) {
		this.requestownerid = requestownerid;
	}

	public long getVolunteeruserid() {
		return volunteeruserid;
	}

	public void setVolunteeruserid(long volunteeruserid) {
		this.volunteeruserid = volunteeruserid;
	}

	public String getVolunteername() {
		return volunteername;
	}

	public void setVolunteername(String volunteername) {
		this.volunteername = volunteername;
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

	public List<NeedsEnum> getNeeds() {
		return needs;
	}

	public void setNeeds(List<NeedsEnum> needs) {
		this.needs = needs;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
