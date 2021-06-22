package com.covid19army.VolunteerService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid19army.VolunteerService.dtos.QuickHelpDto;
import com.covid19army.VolunteerService.services.RequestsQuickHelpService;
import com.covid19army.core.exceptions.NotAuthorizedException;
import com.covid19army.core.exceptions.ResourceNotFoundException;

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
	
	@PostMapping
	public long createQuickHelp(@RequestBody QuickHelpDto quickhelp) throws ResourceNotFoundException, NotAuthorizedException {
		return _requestQuickHelpService.createQuickHelp(quickhelp);
	}
	
	@DeleteMapping("/{quickhelpid}")
	public void deleteQuickHelp(@PathVariable long quickhelpid) throws ResourceNotFoundException, NotAuthorizedException {
		_requestQuickHelpService.deleteQuickHelp(quickhelpid);
	}
}
