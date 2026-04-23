package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequest(
		String patientId,
		String doctorName,
		LocalDate appointmentDate,
		LocalTime appointmentTime,
		String reason,
		String status) {
}
