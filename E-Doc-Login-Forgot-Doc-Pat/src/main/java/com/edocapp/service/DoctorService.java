package com.edocapp.service;

import com.edocapp.model.DoctorDetail;

public interface DoctorService {
	
	public DoctorDetail createDoctor(DoctorDetail doctorDetail);
	public boolean checkPatientEmail(String doctorEmail);
}
