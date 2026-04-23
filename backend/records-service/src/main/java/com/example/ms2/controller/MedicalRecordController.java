package com.example.ms2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms2.dto.MedicalRecordRequest;
import com.example.ms2.model.MedicalRecord;
import com.example.ms2.service.MedicalRecordService;

@RestController
@RequestMapping("/records")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicalRecordController {

	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	@GetMapping
	public List<MedicalRecord> getAllRecords() {
		return medicalRecordService.getAllRecords();
	}

	@GetMapping("/{id}")
	public MedicalRecord getRecordById(@PathVariable String id) {
		return medicalRecordService.getRecordById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MedicalRecord createRecord(@RequestBody MedicalRecordRequest request) {
		return medicalRecordService.createRecord(request);
	}

	@PutMapping("/{id}")
	public MedicalRecord updateRecord(@PathVariable String id, @RequestBody MedicalRecordRequest request) {
		return medicalRecordService.updateRecord(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRecord(@PathVariable String id) {
		medicalRecordService.deleteRecord(id);
	}
}
