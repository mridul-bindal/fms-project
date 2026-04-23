package com.example.ms2.dto;

public record MedicalRecordRequest(String patientId, String diagnosis, String treatment, String notes) {
}
