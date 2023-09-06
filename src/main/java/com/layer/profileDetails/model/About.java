package com.layer.profileDetails.model;

import java.util.List;

import lombok.Data;

@Data
public class About {

	List<String> description;
	
	List<AboutObjects> aboutObjects;
	
	@Data
	public static class AboutObjects{
		String icon;
		
		String value;
		
		String decimals;
		
		String description;
	}
	
}
