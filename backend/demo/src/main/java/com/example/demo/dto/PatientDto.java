package com.example.demo.dto;

public record PatientDto(
		String id,
		String fullName,
		String email,
		Integer age,
		String gender,
		String phone,
		String address,
		String bloodGroup) {
}
