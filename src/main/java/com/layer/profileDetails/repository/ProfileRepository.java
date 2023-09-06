package com.layer.profileDetails.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.layer.profileDetails.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String>{

}
