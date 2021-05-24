package com.covid19army.VolunteerService.models;

import java.io.Serializable;
import javax.persistence.*;

import com.covid19army.VolunteerService.modelListeners.VolunteerModelListener;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the volunteers database table.
 * 
 */
@EntityListeners(VolunteerModelListener.class)
@Entity
@Table(name="volunteers")
@NamedQuery(name="Volunteer.findAll", query="SELECT v FROM Volunteer v")
public class Volunteer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long volunteerid;

	private String contactname;

	private String contactnumber;

	private int countrycode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_updated")
	private Date dateUpdated;

	private String district;

	private boolean isactive;

	private boolean iscontactverified;

	private boolean isdelete;

	private String name;

	private String pincode;

	private String state;

	private int type;

	private long userid;

	//bi-directional many-to-one association to RequestVolunteer
	@OneToMany(mappedBy="volunteer")
	private List<RequestVolunteer> requestvolunteers;

	//bi-directional many-to-one association to VolunteerArea
	@OneToMany(mappedBy="volunteer", cascade = CascadeType.ALL)
	private List<VolunteerArea> volunteerareas;

	//bi-directional many-to-one association to VolunteerProvidedNeed
	@OneToMany(mappedBy="volunteer", cascade = CascadeType.ALL)
	private List<VolunteerProvidedNeed> volunteerprovidedneeds;


	public Volunteer() {
	}
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getVolunteerid() {
		return this.volunteerid;
	}

	public void setVolunteerid(long volunteerid) {
		this.volunteerid = volunteerid;
	}

	public String getContactname() {
		return this.contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public int getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
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

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean getIscontactverified() {
		return this.iscontactverified;
	}

	public void setIscontactverified(boolean iscontactverified) {
		this.iscontactverified = iscontactverified;
	}

	public boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<RequestVolunteer> getRequestvolunteers() {
		return this.requestvolunteers;
	}

	public void setRequestvolunteers(List<RequestVolunteer> requestvolunteers) {
		this.requestvolunteers = requestvolunteers;
	}

	public RequestVolunteer addRequestvolunteer(RequestVolunteer requestvolunteer) {
		getRequestvolunteers().add(requestvolunteer);
		requestvolunteer.setVolunteer(this);

		return requestvolunteer;
	}

	public RequestVolunteer removeRequestvolunteer(RequestVolunteer requestvolunteer) {
		getRequestvolunteers().remove(requestvolunteer);
		requestvolunteer.setVolunteer(null);

		return requestvolunteer;
	}

	public List<VolunteerArea> getVolunteerareas() {
		return this.volunteerareas;
	}

	public void setVolunteerareas(List<VolunteerArea> volunteerareas) {
		this.volunteerareas = volunteerareas;
	}

	public VolunteerArea addVolunteerarea(VolunteerArea volunteerarea) {
		getVolunteerareas().add(volunteerarea);
		volunteerarea.setVolunteer(this);

		return volunteerarea;
	}

	public VolunteerArea removeVolunteerarea(VolunteerArea volunteerarea) {
		getVolunteerareas().remove(volunteerarea);
		volunteerarea.setVolunteer(null);

		return volunteerarea;
	}

	public List<VolunteerProvidedNeed> getVolunteerprovidedneeds() {
		return this.volunteerprovidedneeds;
	}

	public void setVolunteerprovidedneeds(List<VolunteerProvidedNeed> volunteerprovidedneeds) {
		this.volunteerprovidedneeds = volunteerprovidedneeds;
	}

	public VolunteerProvidedNeed addVolunteerprovidedneed(VolunteerProvidedNeed volunteerprovidedneed) {
		getVolunteerprovidedneeds().add(volunteerprovidedneed);
		volunteerprovidedneed.setVolunteer(this);

		return volunteerprovidedneed;
	}

	public VolunteerProvidedNeed removeVolunteerprovidedneed(VolunteerProvidedNeed volunteerprovidedneed) {
		getVolunteerprovidedneeds().remove(volunteerprovidedneed);
		volunteerprovidedneed.setVolunteer(null);

		return volunteerprovidedneed;
	}
}