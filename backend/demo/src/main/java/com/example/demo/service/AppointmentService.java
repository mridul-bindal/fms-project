package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AppointmentRequest;
import com.example.demo.dto.AppointmentResponse;

public interface AppointmentService {

	List<AppointmentResponse> getAllAppointments();

	AppointmentResponse getAppointmentById(String id);

	AppointmentResponse createAppointment(AppointmentRequest request);

	AppointmentResponse updateAppointment(String id, AppointmentRequest request);

	void deleteAppointment(String id);
}
