package com.pofil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.InsuranceCustomerRegister;

public interface InsuranceCustomerRegisterRepository extends MongoRepository<InsuranceCustomerRegister, String> {
	Optional<InsuranceCustomerRegister> findByMasterPolicyId(String masterPolicyId);

	@Override
	List<InsuranceCustomerRegister> findAll();
}
