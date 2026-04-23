package com.example.ms2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medical_records")
public record MedicalRecord(@Id String id, String patientId, String diagnosis, String treatment, String notes) {
}
