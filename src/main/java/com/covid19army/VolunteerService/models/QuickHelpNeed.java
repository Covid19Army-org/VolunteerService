package com.covid19army.VolunteerService.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.covid19army.core.enums.NeedsEnum;

@Entity
@Table(name="quickhelpneeds")
public class QuickHelpNeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quickhelpneedid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="quickhelpid")
	private QuickHelp quickhelp;
	
	@Enumerated(EnumType.ORDINAL)
	private NeedsEnum needid;
	
	public QuickHelpNeed() {}
	
	public QuickHelpNeed(NeedsEnum need, QuickHelp quickHelp) {
		this.quickhelp = quickHelp;
		this.needid = need;
	}

	public long getQuickhelpneedid() {
		return quickhelpneedid;
	}

	public void setQuickhelpneedid(long quickhelpneedid) {
		this.quickhelpneedid = quickhelpneedid;
	}

	public QuickHelp getQuickhelp() {
		return quickhelp;
	}

	public void setQuickhelp(QuickHelp quickhelp) {
		this.quickhelp = quickhelp;
	}

	public NeedsEnum getNeedid() {
		return needid;
	}

	public void setNeedid(NeedsEnum needid) {
		this.needid = needid;
	}

	
}
