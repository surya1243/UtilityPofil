package com.pofil.repository;

import com.pofil.model.InsuranceCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InsuranceCustomerRepository extends MongoRepository<InsuranceCustomer, String> {
    InsuranceCustomer findInsuranceCustomerByBankAccNo(String accountNo);

    Optional<InsuranceCustomer> findInsuranceCustomerByClientCode(int cCode);
}
