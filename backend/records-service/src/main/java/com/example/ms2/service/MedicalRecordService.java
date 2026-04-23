package com.example.ms2.service;

import java.util.List;

import com.example.ms2.dto.MedicalRecordRequest;
import com.example.ms2.model.MedicalRecord;

public interface MedicalRecordService {
	List<MedicalRecord> getAllRecords();

	MedicalRecord getRecordById(String id);

	MedicalRecord createRecord(MedicalRecordRequest request);

	MedicalRecord updateRecord(String id, MedicalRecordRequest request);

	void deleteRecord(String id);
}
