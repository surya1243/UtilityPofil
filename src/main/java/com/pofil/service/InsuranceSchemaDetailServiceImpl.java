package com.pofil.service;

import com.pofil.model.InsuranceSchema;
import com.pofil.repository.InsuranceSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceSchemaDetailServiceImpl implements InsuranceSchemaDetailService {

    private InsuranceSchemaRepository schemaRepository;

    @Autowired
    public InsuranceSchemaDetailServiceImpl(InsuranceSchemaRepository schemaRepository) {
        this.schemaRepository = schemaRepository;
    }
    @Override
    public Optional<InsuranceSchema> getInsuranceSchemaById(String id) {
        return Optional.empty();
    }

    @Override
    public InsuranceSchema getInsuranceSchemaByInsSchemaCode(String insCode) {
        return schemaRepository.findByInsSchemaCode(insCode);
    }

    @Override
    public List<InsuranceSchema> getInsuranceSchemas() {
        return schemaRepository.findAll();
    }

    @Override
    public InsuranceSchema saveInsuranceSchema(InsuranceSchema insuranceSchema) {
        schemaRepository.save(insuranceSchema);
        return schemaRepository.save(insuranceSchema);
    }
}
