package com.edocapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edocapp.model.PatientDetail;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetail, Integer>{

	public boolean existsByPatientEmail(String patientEmail);
	public PatientDetail findByPatientEmail(String patientEmail);
}
