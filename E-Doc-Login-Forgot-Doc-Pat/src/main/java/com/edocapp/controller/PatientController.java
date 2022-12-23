package com.edocapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edocapp.model.PatientDetail;
import com.edocapp.repository.PatientRepository;


@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		String email = p.getName();
		PatientDetail patient = patientRepository.findByPatientEmail(email);

		m.addAttribute("patient", patient);

	}

	@GetMapping("/home")
	public String viewPatientHome() {
		return "patient/patient_home";
	}
	
}
