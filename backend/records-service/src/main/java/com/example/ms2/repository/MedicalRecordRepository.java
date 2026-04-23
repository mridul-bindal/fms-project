package com.example.ms2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ms2.model.MedicalRecord;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
}
