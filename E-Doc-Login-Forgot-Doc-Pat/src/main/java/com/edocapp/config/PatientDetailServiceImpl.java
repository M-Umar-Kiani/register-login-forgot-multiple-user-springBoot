package com.edocapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edocapp.model.PatientDetail;
import com.edocapp.repository.PatientRepository;

@Service
public class PatientDetailServiceImpl implements UserDetailsService{

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String patientEmail) throws UsernameNotFoundException {
		
		PatientDetail patient = patientRepository.findByPatientEmail(patientEmail);
		if(patient != null) {
			return new CustomPatientDetail(patient);
		}
		throw new UsernameNotFoundException("User not available");
	}

}
