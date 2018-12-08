package com.pofil.repository;

import com.pofil.model.InsuranceSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InsuranceSchemaRepository extends MongoRepository<InsuranceSchema, String> {
    InsuranceSchema findByInsSchemaCode(String schemaCode);
}
