package com.layer.profileDetails.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.layer.profileDetails.model.Contact;
import com.layer.profileDetails.model.Profile;
import com.layer.profileDetails.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfileDataFetcher {
	
	@Autowired
	ProfileRepository profileRepository;
	
	public Profile findProfileById(String id) {
		return profileRepository.findById(id).get();
	}
	
	public Profile getProfileDetails(String id,String user,String indicationType,String email) {
		if(Objects.nonNull(findProfileById(id))) {
			return findProfileById(id);
		}
		Profile profile = new Profile();
		profile.setId(id);
		profile.setName(user);
		List<Profile> foundProfiles = profileRepository.findAll(Example.of(profile, ExampleMatcher.matching().withIgnoreCase().withIgnorePaths("experience")));
		profile.setIndicationType(indicationType);
		Contact contact = new Contact();
		contact.setEmail(email);
		profile.setContact(contact);
		log.info("Search Profile :: {} ",profile);
		Profile foundProfile = null;
		if(foundProfiles.size()>1) {
			foundProfile = profileRepository.findOne(Example.of(profile, ExampleMatcher.matching().withIgnoreCase().withIgnorePaths("experience"))).orElse(null);
		}else if(Objects.isNull(foundProfile)&&foundProfiles.size()>0){
			foundProfile = foundProfiles.get(0);
		}
		log.info("Found profile :: {}",foundProfile);
		return foundProfile;
	}
	
	public Profile getExactProfile(String indicationType,String email) {
		Profile profile = new Profile();
		profile.setIndicationType(indicationType);
		Contact contact = new Contact();
		contact.setEmail(email);
		profile.setContact(contact);
		Profile foundProfile = profileRepository.findOne(Example.of(profile, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
		log.info("Search Profile :: {} ",profile);
		log.info("Found profile :: {}",foundProfile);
		return foundProfile;
	}
	
	public Profile saveProfile(Profile profile) {
		Profile foundProfile = getExactProfile(profile.getIndicationType(),profile.getContact().getEmail());
		if(Objects.isNull(foundProfile)) {
			Profile prof = profileRepository.save(profile);
			log.info("Save Profile :: {}",prof);
			return prof;
		}else{
			log.info("Profile already Exists in save - Retrieved :: {}",foundProfile);
			return foundProfile;
		}
		
	}
	
	public Profile replaceProfile(Profile profile) {
		Profile foundProfile = getExactProfile(profile.getIndicationType(),profile.getContact().getEmail());
		if(Objects.isNull(foundProfile)) {
			Profile prof = profileRepository.save(profile);
			log.info("Created Profile :: {}",prof);
			return prof;
		}else {
			String id = foundProfile.getId();
			profile.setId(id);
			profileRepository.save(profile);
			log.info("Profile Updated Successfully - :: {}",foundProfile);
			return foundProfile;
		}
		
	}

}
