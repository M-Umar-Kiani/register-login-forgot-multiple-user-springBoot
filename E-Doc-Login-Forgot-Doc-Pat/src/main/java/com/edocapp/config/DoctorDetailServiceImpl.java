package com.edocapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edocapp.model.DoctorDetail;
import com.edocapp.repository.DoctorRepository;

@Service
public class DoctorDetailServiceImpl implements UserDetailsService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String doctorEmail) throws UsernameNotFoundException {
		
		DoctorDetail doctor = doctorRepository.findByDoctorEmail(doctorEmail);
		if(doctor != null) {
			return new CustomDoctorDetail(doctor);
		}
		throw new UsernameNotFoundException("doctor not available");
	}

}
