package com.edocapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DoctorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	private String doctorName;
	private String qualification;
	private String experience;
	private String specialization;
	private String doctorEmail;
	private String doctorPassword;
	
	public DoctorDetail() {
		super();
	}

	public DoctorDetail(String doctorName, String qualification, String experience, String specialization,
			String doctorEmail, String doctorPassword, String role) {
		super();
		this.doctorName = doctorName;
		this.qualification = qualification;
		this.experience = experience;
		this.specialization = specialization;
		this.doctorEmail = doctorEmail;
		this.doctorPassword = doctorPassword;
	}


	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	@Override
	public String toString() {
		return "DoctorDetail [doctorId=" + doctorId + ", doctorName=" + doctorName + ", qualification=" + qualification
				+ ", experience=" + experience + ", specialization=" + specialization + ", doctorEmail=" + doctorEmail
				+ ", doctorPassword=" + doctorPassword + "]";
	}	
	
	
}
