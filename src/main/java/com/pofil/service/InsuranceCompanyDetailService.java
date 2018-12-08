package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.Branch;
import com.pofil.model.InsuranceCompany;

public interface InsuranceCompanyDetailService {
	
	Optional<InsuranceCompany> getInsCompanyById(String id);

	InsuranceCompany getInsCompanyByCode(String insCompanyCode);

	List<InsuranceCompany> getAllInsCompany();

	InsuranceCompany saveInsCompany(InsuranceCompany insCompany);

}
