package com.example.ms2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ms2.dto.MedicalRecordRequest;
import com.example.ms2.model.MedicalRecord;
import com.example.ms2.repository.MedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	private final MedicalRecordRepository medicalRecordRepository;

	public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	@Override
	public List<MedicalRecord> getAllRecords() {
		return medicalRecordRepository.findAll();
	}

	@Override
	public MedicalRecord getRecordById(String id) {
		return medicalRecordRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found: " + id));
	}

	@Override
	public MedicalRecord createRecord(MedicalRecordRequest request) {
		return medicalRecordRepository.save(
				new MedicalRecord(null, request.patientId(), request.diagnosis(), request.treatment(), request.notes()));
	}

	@Override
	public MedicalRecord updateRecord(String id, MedicalRecordRequest request) {
		getRecordById(id);
		return medicalRecordRepository.save(
				new MedicalRecord(id, request.patientId(), request.diagnosis(), request.treatment(), request.notes()));
	}

	@Override
	public void deleteRecord(String id) {
		getRecordById(id);
		medicalRecordRepository.deleteById(id);
	}
}
