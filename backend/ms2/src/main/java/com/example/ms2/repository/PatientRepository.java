package com.example.ms2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ms2.model.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
