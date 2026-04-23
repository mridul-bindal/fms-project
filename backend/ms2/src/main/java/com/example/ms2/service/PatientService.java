package com.example.ms2.service;

import java.util.List;

import com.example.ms2.dto.PatientRequest;
import com.example.ms2.model.Patient;

public interface PatientService {

	List<Patient> getAllPatients();

	Patient getPatientById(String id);

	Patient createPatient(PatientRequest request);

	Patient updatePatient(String id, PatientRequest request);

	void deletePatient(String id);
}
