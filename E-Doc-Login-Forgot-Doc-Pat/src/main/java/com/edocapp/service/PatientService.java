package com.edocapp.service;

import com.edocapp.model.PatientDetail;

public interface PatientService {

	public PatientDetail createPatient(PatientDetail patientDetail);
	public boolean checkPatientEmail(String patientEmail);
}
