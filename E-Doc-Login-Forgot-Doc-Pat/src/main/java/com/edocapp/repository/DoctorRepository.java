package com.edocapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edocapp.model.DoctorDetail;
import com.edocapp.model.PatientDetail;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetail, Integer>{

	public boolean existsByDoctorEmail(String doctorEmail);
	public DoctorDetail findByDoctorEmail(String doctorEmail);
	
}
