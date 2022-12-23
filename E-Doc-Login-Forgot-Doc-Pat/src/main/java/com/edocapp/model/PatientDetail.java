package com.edocapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	private String patientName;
	private String address;
	private Long mobileNumber;
	private String patientEmail;
	private String patientPassword;
	private String role;
	
	public PatientDetail() {
		super();
	}

	public PatientDetail(String patientName, String address, Long mobileNumber, String patientEmail,
			String patientPassword, String role) {
		super();
		this.patientName = patientName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.patientEmail = patientEmail;
		this.patientPassword = patientPassword;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}

	@Override
	public String toString() {
		return "PatientDetail [patientId=" + patientId + ", patientName=" + patientName + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", patientEmail=" + patientEmail + ", patientPassword="
				+ patientPassword + "]";
	}	
	
	
}
