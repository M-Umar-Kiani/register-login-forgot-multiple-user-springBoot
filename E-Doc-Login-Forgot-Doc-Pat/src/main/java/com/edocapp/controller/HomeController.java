package com.edocapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.edocapp.model.DoctorDetail;
import com.edocapp.model.PatientDetail;
import com.edocapp.repository.DoctorRepository;
import com.edocapp.repository.PatientRepository;
import com.edocapp.service.DoctorService;
import com.edocapp.service.PatientService;

@Controller
public class HomeController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	@ModelAttribute
	private void patientDetails(Model m, Principal p) {
		if(p != null) {
			String email = p.getName();
			PatientDetail patient = patientRepository.findByPatientEmail(email);
			m.addAttribute("patient", patient);
		}
	}

	@ModelAttribute
	private void doctorDetails(Model m, Principal p) {
		if(p != null) {
			String email = p.getName();
			DoctorDetail doctor = doctorRepository.findByDoctorEmail(email);
			m.addAttribute("doctor", doctor);
		}
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
		
	@GetMapping("/patient/login")
	public String viewLoginPatient() {
		return "patient/patient_login";
	}
	
	@GetMapping("/doctor/login")
	public String viewLoginDoctor() {
		return "doctor/doctor_login";
	}
	
	@GetMapping("/patientRegister")
	public String viewRegisterPatient() {
		return "/register_patient";
	}
	
	@GetMapping("/doctorRegister")
	public String viewRegisterDoctor() {
		return "/register_doctor";
	}

	@PostMapping("/createPatient")
	public String createPatient(@ModelAttribute PatientDetail patient, HttpSession session) {
		System.out.println(patient);
		
		boolean f = patientService.checkPatientEmail(patient.getPatientEmail());
		if(f) {
			//System.out.println("Email already exist");
			session.setAttribute("msg", "Email id already exist");
		}else {
			PatientDetail patientDetail =  patientService.createPatient(patient);
			if(patientDetail != null) {
				System.out.println("Register Successfully");
				session.setAttribute("msg", "Register Successfully");
			}else {
				System.out.println("Something wrong on server");
				session.setAttribute("msg", "Something wrong on server");
			}
		}
		return "redirect:/patientRegister";
	}
	
	
	@PostMapping("/createDoctor")
	public String createDoctor(@ModelAttribute DoctorDetail doctor, HttpSession session) {
		System.out.println(doctor);
		
		boolean f = patientService.checkPatientEmail(doctor.getDoctorEmail());
		if(f) {
			//System.out.println("Email already exist");
			session.setAttribute("msg", "Email id already exist");
		}else {
			DoctorDetail doctorDetail =  doctorService.createDoctor(doctor);
			if(doctorDetail != null) {
				//System.out.println("Register Successfully");
				session.setAttribute("msg", "Register Successfully");
			}else {
				//System.out.println("Something wrong on server");
				session.setAttribute("msg", "Something wrong on server");
			}
		}
		return "redirect:/doctorRegister";
	}
	
}
