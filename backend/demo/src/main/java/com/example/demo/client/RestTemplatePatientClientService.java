package com.example.demo.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.PatientDto;

@Service
public class RestTemplatePatientClientService implements PatientClientService {

	private final RestTemplate restTemplate;

	public RestTemplatePatientClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public PatientDto getPatientById(String patientId) {
		try {
			return restTemplate.getForObject("http://patient-service/patients/{id}", PatientDto.class, patientId);
		} catch (HttpClientErrorException.NotFound ex) {
			return null;
		}
	}
}
