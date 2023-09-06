package com.layer.profileDetails.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.layer.profileDetails.model.Profile;
import com.layer.profileDetails.model.UserMessage;
import com.layer.profileDetails.service.ProfileDataFetcher;

@RestController
@RequestMapping("/profile-details")
@CrossOrigin
public class ProfileController {
	
	@Autowired
	ProfileDataFetcher dataFetcher;
	
	@PostMapping("/create")
	public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
		Profile profileCreated = dataFetcher.saveProfile(profile);
		UserMessage message = new UserMessage();
		if(Objects.nonNull(profileCreated)) {
			message.setId(profile.getId());
			message.setMessage("Created Successfully");
			return new ResponseEntity<>(message,HttpStatus.CREATED);
		}else {
			message.setMessage("User already exists, try updating profile with put call instead");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
	}
	
	@PostMapping("/replace")
	public ResponseEntity<?> replaceProfile(@RequestBody Profile profile) {
		Profile profileCreated = dataFetcher.replaceProfile(profile);
		UserMessage message = new UserMessage();
		if(Objects.nonNull(profileCreated)) {
			message.setId(profileCreated.getId());
			message.setMessage("Replaced Successfully");
			return new ResponseEntity<>(message,HttpStatus.CREATED);
		}else {
			message.setMessage("Replaced profile successfully");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getProfileDetails(@RequestParam(name="id",required = false) String id,@RequestParam(name="name",required = false) String name,
			@RequestParam(name = "indicationType",required = false) String indicationType,
			@RequestParam(name = "email",required = false) String email) {
		Profile profile = dataFetcher.getProfileDetails(id,name,indicationType,email);
		return new ResponseEntity<>(profile,HttpStatus.OK);
	}

}
