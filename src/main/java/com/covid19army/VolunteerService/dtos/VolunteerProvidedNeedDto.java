package com.covid19army.VolunteerService.dtos;


import com.covid19army.core.enums.NeedsEnum;

public class VolunteerProvidedNeedDto {
	private long volunterprovidedneedid;

	private NeedsEnum needid;

	public long getVolunterprovidedneedid() {
		return volunterprovidedneedid;
	}

	public void setVolunterprovidedneedid(long volunterprovidedneedid) {
		this.volunterprovidedneedid = volunterprovidedneedid;
	}

	public NeedsEnum getNeedid() {
		return needid;
	}

	public void setNeedid(NeedsEnum needid) {
		this.needid = needid;
	}
	
	
}
