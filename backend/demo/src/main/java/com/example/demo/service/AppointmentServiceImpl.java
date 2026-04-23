package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.client.PatientClientService;
import com.example.demo.dto.AppointmentRequest;
import com.example.demo.dto.AppointmentResponse;
import com.example.demo.dto.PatientDto;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final PatientClientService patientClientService;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientClientService patientClientService) {
		this.appointmentRepository = appointmentRepository;
		this.patientClientService = patientClientService;
	}

	@Override
	public List<AppointmentResponse> getAllAppointments() {
		return appointmentRepository.findAll().stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public AppointmentResponse getAppointmentById(String id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found: " + id));
		return toResponse(appointment);
	}

	@Override
	public AppointmentResponse createAppointment(AppointmentRequest request) {
		PatientDto patient = patientClientService.getPatientById(request.patientId());
		if (patient == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not found: " + request.patientId());
		}
		Appointment appointment = new Appointment(
				null,
				patient.id(),
				request.doctorName(),
				request.appointmentDate(),
				request.appointmentTime(),
				request.reason(),
				request.status());
		return toResponse(appointmentRepository.save(appointment), patient);
	}

	@Override
	public AppointmentResponse updateAppointment(String id, AppointmentRequest request) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found: " + id));
		PatientDto patient = patientClientService.getPatientById(request.patientId());
		if (patient == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not found: " + request.patientId());
		}
		appointment.setPatientId(patient.id());
		appointment.setDoctorName(request.doctorName());
		appointment.setAppointmentDate(request.appointmentDate());
		appointment.setAppointmentTime(request.appointmentTime());
		appointment.setReason(request.reason());
		appointment.setStatus(request.status());
		return toResponse(appointmentRepository.save(appointment), patient);
	}

	@Override
	public void deleteAppointment(String id) {
		appointmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found: " + id));
		appointmentRepository.deleteById(id);
	}

	private AppointmentResponse toResponse(Appointment appointment) {
		PatientDto patient = patientClientService.getPatientById(appointment.getPatientId());
		return toResponse(appointment, patient);
	}

	private AppointmentResponse toResponse(Appointment appointment, PatientDto patient) {
		return new AppointmentResponse(
				appointment.getId(),
				appointment.getPatientId(),
				appointment.getDoctorName(),
				appointment.getAppointmentDate(),
				appointment.getAppointmentTime(),
				appointment.getReason(),
				appointment.getStatus(),
				patient);
	}
}
