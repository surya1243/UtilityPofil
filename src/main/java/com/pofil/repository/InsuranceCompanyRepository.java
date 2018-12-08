package com.pofil.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.InsuranceCompany;

public interface InsuranceCompanyRepository extends MongoRepository<InsuranceCompany, String>{
	InsuranceCompany findByInsCompanyCode(String insCode);

}
