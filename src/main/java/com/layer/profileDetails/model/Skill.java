package com.layer.profileDetails.model;

import java.util.List;

import lombok.Data;

@Data
public class Skill {

	String description;
	
	List<Skills> skills;
	
	@Data
	public static class Skills{
		String name;
		String value;
	}
	
}
