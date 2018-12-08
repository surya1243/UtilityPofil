package com.pofil.service;

import com.pofil.model.InsuranceSchema;

import java.util.List;
import java.util.Optional;

public interface InsuranceSchemaDetailService {

    Optional<InsuranceSchema> getInsuranceSchemaById(String id);

    InsuranceSchema getInsuranceSchemaByInsSchemaCode(String insCode);

    List<InsuranceSchema> getInsuranceSchemas();

    InsuranceSchema saveInsuranceSchema(InsuranceSchema insuranceSchema);
}
