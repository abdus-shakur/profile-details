package com.layer.profileDetails.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("profile-details-data")
public class Profile {
	@Id
	String id;
	
	String name;
	
	String indicationType;
	
	Date experience = new Date();
	
	String experienceValue;
	
	String designation;
	
	String resumeUrl;
	
	About about;
	
	Skill skill;
	
	List<Certification> certification;
	
	Contact contact;
	
	ChronoData awards;
	
	ChronoData resume;
	

}
