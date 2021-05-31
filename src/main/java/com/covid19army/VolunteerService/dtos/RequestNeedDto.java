package com.covid19army.VolunteerService.dtos;

import com.covid19army.core.enums.NeedsEnum;

public class RequestNeedDto {
	private NeedsEnum needid;

	public RequestNeedDto() {
	}

	public NeedsEnum getNeedid() {
		return needid;
	}

	public void setNeedid(NeedsEnum needid) {
		this.needid = needid;
	}
}