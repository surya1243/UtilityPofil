package com.pofil.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pofil.model.UtilityBills;

public interface UtilityBillsRepository extends MongoRepository<UtilityBills, String> {
	
	Optional<UtilityBills> findById(String id);

}
