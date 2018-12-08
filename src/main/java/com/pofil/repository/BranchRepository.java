package com.pofil.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pofil.model.Branch;

public interface BranchRepository extends MongoRepository<Branch, String> {
	Branch findByBranchName(String branchName);
	

}
