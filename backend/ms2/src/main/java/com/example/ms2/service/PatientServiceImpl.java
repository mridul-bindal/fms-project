package com.example.ms2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ms2.dto.PatientRequest;
import com.example.ms2.model.Patient;
import com.example.ms2.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(String id) {
		return patientRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found: " + id));
	}

	@Override
	public Patient createPatient(PatientRequest request) {
		Patient patient = toPatient(request);
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(String id, PatientRequest request) {
		Patient existingPatient = getPatientById(id);
		existingPatient.setFullName(request.fullName());
		existingPatient.setEmail(request.email());
		existingPatient.setAge(request.age());
		existingPatient.setGender(request.gender());
		existingPatient.setPhone(request.phone());
		existingPatient.setAddress(request.address());
		existingPatient.setBloodGroup(request.bloodGroup());
		return patientRepository.save(existingPatient);
	}

	@Override
	public void deletePatient(String id) {
		Patient existingPatient = getPatientById(id);
		patientRepository.delete(existingPatient);
	}

	private Patient toPatient(PatientRequest request) {
		Patient patient = new Patient();
		patient.setFullName(request.fullName());
		patient.setEmail(request.email());
		patient.setAge(request.age());
		patient.setGender(request.gender());
		patient.setPhone(request.phone());
		patient.setAddress(request.address());
		patient.setBloodGroup(request.bloodGroup());
		return patient;
	}
}
