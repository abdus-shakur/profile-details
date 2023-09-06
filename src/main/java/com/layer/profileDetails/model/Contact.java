package com.layer.profileDetails.model;

import lombok.Data;

@Data
public class Contact {
	
	String description;
	Location location;
	String email;
	String twitter;
	String facebook;
	String linkedIn;
	String messageReference;
	
	@Data
	public static class Location{
		String name;
		String url;
	}

}
