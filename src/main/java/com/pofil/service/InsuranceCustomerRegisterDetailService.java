package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.InsuranceCustomerRegister;

public interface InsuranceCustomerRegisterDetailService {
	Optional<InsuranceCustomerRegister> getInsCustById(String id);

	List<InsuranceCustomerRegister> getAllInsuranceCustomerRegister();

	InsuranceCustomerRegister saveInsCustRegister(InsuranceCustomerRegister insCustRegister);
}
