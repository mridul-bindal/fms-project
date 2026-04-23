package com.example.ms2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
public class Patient {

	@Id
	private String id;
	private String fullName;
	private String email;
	private Integer age;
	private String gender;
	private String phone;
	private String address;
	private String bloodGroup;

	public Patient() {
	}

	public Patient(String id, String fullName, String email, Integer age, String gender, String phone, String address,
			String bloodGroup) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.bloodGroup = bloodGroup;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
}
