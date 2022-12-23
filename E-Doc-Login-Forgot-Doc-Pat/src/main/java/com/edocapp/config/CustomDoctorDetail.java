package com.edocapp.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edocapp.model.DoctorDetail;

public class CustomDoctorDetail implements UserDetails {

	private DoctorDetail doctor;
	
	public CustomDoctorDetail(DoctorDetail doctor) {
		super();
		this.doctor = doctor;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority simpleGrantedAuthority = new 
//				SimpleGrantedAuthority(doctor.getRole());	
//		return Arrays.asList(simpleGrantedAuthority);
		return null;
	}

	@Override
	public String getPassword() {
		return doctor.getDoctorPassword();
	}

	@Override
	public String getUsername() {
		return doctor.getDoctorEmail();
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
