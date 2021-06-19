package com.covid19army.VolunteerService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid19army.VolunteerService.dtos.QuickHelpDto;
import com.covid19army.VolunteerService.services.RequestsQuickHelpService;

@RestController
@RequestMapping("quickhelp")
public class RequestsQuickHelpController {
	
	@Autowired
	RequestsQuickHelpService _requestQuickHelpService;
	
	@GetMapping
	public List<QuickHelpDto> getQuickHelp(){
		var result = _requestQuickHelpService.getQuickHelpsForUser();
		return result;
	}
}
