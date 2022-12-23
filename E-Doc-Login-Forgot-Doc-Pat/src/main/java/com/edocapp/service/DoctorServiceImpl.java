package com.edocapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edocapp.model.DoctorDetail;
import com.edocapp.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public DoctorDetail createDoctor(DoctorDetail doctorDetail) {
		doctorDetail.setDoctorPassword(
				bCryptPasswordEncoder.encode(doctorDetail.getDoctorPassword()));
//		doctorDetail.setRole("ROLE_ADMIN");
		return doctorRepository.save(doctorDetail);
	}

	@Override
	public boolean checkPatientEmail(String doctorEmail) {
		return doctorRepository.existsByDoctorEmail(doctorEmail);
	}
}
