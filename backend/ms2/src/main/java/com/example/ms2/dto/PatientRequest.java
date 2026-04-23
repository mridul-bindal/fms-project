package com.example.ms2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(
		@NotBlank String fullName,
		@NotBlank @Email String email,
		@NotNull @Min(0) @Max(120) Integer age,
		@NotBlank String gender,
		@NotBlank String phone,
		@NotBlank String address,
		@NotBlank String bloodGroup) {
}
