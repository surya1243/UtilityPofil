package com.pofil.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.InsuranceCustomerRegister;

public interface InsuranceCustomerRegisterRepository extends MongoRepository<InsuranceCustomerRegister, String> {
	Optional<InsuranceCustomerRegister> findByMasterPolicyId(String masterPolicyId);

	List<InsuranceCustomerRegister> findByInsCompanyName(String insCompanyName);

	List<InsuranceCustomerRegister> findByInsCompanyNameAndInsSchemaName(String insCompanyName, String insSchemeName);

	List<InsuranceCustomerRegister> findByInsStartedDateIsBetween(Date firstDate, Date secondDate);

	@Override
	List<InsuranceCustomerRegister> findAll();
}
