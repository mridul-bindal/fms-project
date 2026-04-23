package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointment {

	@Id
	private String id;
	private String patientId;
	private String doctorName;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private String reason;
	private String status;

	public Appointment() {
	}

	public Appointment(String id, String patientId, String doctorName, LocalDate appointmentDate, LocalTime appointmentTime,
			String reason, String status) {
		this.id = id;
		this.patientId = patientId;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.reason = reason;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
