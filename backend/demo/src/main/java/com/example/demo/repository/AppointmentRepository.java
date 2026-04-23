package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
