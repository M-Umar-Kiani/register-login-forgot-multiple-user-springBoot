package com.edocapp.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edocapp.model.PatientDetail;

public class CustomPatientDetail implements UserDetails {

	private PatientDetail patient;
	
	public CustomPatientDetail(PatientDetail patient) {
		super();
		this.patient = patient;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority simpleGrantedAuthority = new 
//				SimpleGrantedAuthority(patient.getRole());	
//		return Arrays.asList(simpleGrantedAuthority);
		return null;
	}

	@Override
	public String getPassword() {
		return patient.getPatientPassword();
	}

	@Override
	public String getUsername() {
		return patient.getPatientEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
