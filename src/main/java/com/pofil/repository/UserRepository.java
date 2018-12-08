package com.pofil.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.AppUser;

public interface UserRepository extends MongoRepository<AppUser, String> {
	AppUser findByEmail(String email);

}
