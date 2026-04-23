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

import com.example.ms2.dto.PatientRequest;
import com.example.ms2.model.Patient;
import com.example.ms2.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable String id) {
		return patientService.getPatientById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Patient createPatient(@Valid @RequestBody PatientRequest request) {
		return patientService.createPatient(request);
	}

	@PutMapping("/{id}")
	public Patient updatePatient(@PathVariable String id, @Valid @RequestBody PatientRequest request) {
		return patientService.updatePatient(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePatient(@PathVariable String id) {
		patientService.deletePatient(id);
	}
}
