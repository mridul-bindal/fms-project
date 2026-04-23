package com.example.demo.controller;

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

import com.example.demo.dto.AppointmentRequest;
import com.example.demo.dto.AppointmentResponse;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {

	private final AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping
	public List<AppointmentResponse> getAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@GetMapping("/{id}")
	public AppointmentResponse getAppointmentById(@PathVariable String id) {
		return appointmentService.getAppointmentById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AppointmentResponse createAppointment(@RequestBody AppointmentRequest request) {
		return appointmentService.createAppointment(request);
	}

	@PutMapping("/{id}")
	public AppointmentResponse updateAppointment(@PathVariable String id, @RequestBody AppointmentRequest request) {
		return appointmentService.updateAppointment(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAppointment(@PathVariable String id) {
		appointmentService.deleteAppointment(id);
	}
}
