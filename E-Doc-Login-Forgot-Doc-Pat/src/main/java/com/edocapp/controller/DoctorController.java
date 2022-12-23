package com.edocapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edocapp.model.DoctorDetail;
import com.edocapp.repository.DoctorRepository;


@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	@ModelAttribute
	private void doctorDetails(Model m, Principal p) {
		String email = p.getName();
		DoctorDetail doctor = doctorRepository.findByDoctorEmail(email);

		m.addAttribute("doctor", doctor);

	}

	@GetMapping("/home")
	public String viewDoctorHome() {
		return "doctor/doctor_home";
	}
}
