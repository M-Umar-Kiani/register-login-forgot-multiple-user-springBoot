package com.edocapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edocapp.model.PatientDetail;
import com.edocapp.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public PatientDetail createPatient(PatientDetail patientDetail) {
		patientDetail.setPatientPassword(
				bCryptPasswordEncoder.encode(patientDetail.getPatientPassword()));
//		patientDetail.setRole("ROLE_USER");
		return patientRepository.save(patientDetail);
	}

	@Override
	public boolean checkPatientEmail(String patientEmail) {
		return patientRepository.existsByPatientEmail(patientEmail);
	}

}
