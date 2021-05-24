package com.covid19army.VolunteerService.models;

import java.io.Serializable;
import javax.persistence.*;

import com.covid19army.core.enums.NeedsEnum;


/**
 * The persistent class for the volunteerprovidedneeds database table.
 * 
 */
@Entity
@Table(name="volunteerprovidedneeds")
@NamedQuery(name="VolunteerProvidedNeed.findAll", query="SELECT v FROM VolunteerProvidedNeed v")
public class VolunteerProvidedNeed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long volunterprovidedneedid;

	@Enumerated(EnumType.ORDINAL)
	private NeedsEnum needid;

	//bi-directional many-to-one association to Volunteer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="volunteerid")
	private Volunteer volunteer;

	public VolunteerProvidedNeed() {
	}

	public VolunteerProvidedNeed(NeedsEnum need, Volunteer volunteer) {
		this.needid = need;
		this.volunteer = volunteer;
	}
	public long getVolunterprovidedneedid() {
		return this.volunterprovidedneedid;
	}

	public void setVolunterprovidedneedid(long volunterprovidedneedid) {
		this.volunterprovidedneedid = volunterprovidedneedid;
	}

	public NeedsEnum getNeedid() {
		return this.needid;
	}

	public void setNeedid(NeedsEnum needid) {
		this.needid = needid;
	}

	public Volunteer getVolunteer() {
		return this.volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

}