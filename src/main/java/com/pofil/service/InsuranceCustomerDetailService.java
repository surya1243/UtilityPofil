package com.pofil.service;

import com.pofil.model.InsuranceCustomer;

import java.util.List;
import java.util.Optional;

public interface InsuranceCustomerDetailService {
    Optional<InsuranceCustomer> getInsCompanyById(String id);

    InsuranceCustomer getInsuranceCustomerByName(String insCustomerName);

    Optional<InsuranceCustomer> getInsuranceCustomerByClientCode(int cCode);

    List<InsuranceCustomer> getAllInsuranceCustomers();

    InsuranceCustomer saveInsCustomer(InsuranceCustomer insuranceCustomer);

}
