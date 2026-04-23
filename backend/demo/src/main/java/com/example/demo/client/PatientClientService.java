package com.example.demo.client;

import com.example.demo.dto.PatientDto;

public interface PatientClientService {

	PatientDto getPatientById(String patientId);
}
