package com.edocapp.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edocapp.model.DoctorDetail;
import com.edocapp.model.PatientDetail;
import com.edocapp.repository.DoctorRepository;
import com.edocapp.repository.PatientRepository;
import com.edocapp.service.EmailService;

@Controller
public class FotgotController {

	
	@Autowired
	private EmailService emailService;
		
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	Random random = new Random(1000);
	
	//email id form open handler
	@GetMapping("/forgot-patient")
	public String openEmailForm() {
		return "forgot_email_form_patient";
	}
	
	@GetMapping("/forgot-doctor")
	public String openEmailFormDoc() {
		return "forgot_email_form_doctor";
	}
	
	@PostMapping("/send-otp-patient-email")
	public String sendOTPPatientEmail(@RequestParam("email") String email, HttpSession session) {
		
		System.out.println("Email id is :" + email);
		
		//generating otp for 4 digits
		int otp = random.nextInt(999999);
		System.out.println("OTP "+ otp);
		
		
		//Write code for send OTP to email...
		String subject = "OTP From E-Doc";
		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
				+"<h3>"
				+"Your OTP is"
				+"<br>"
				+"" +otp
				+"</h3>"
				+"</div>"; 
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			
			return "verify_otp_patient";
			
		} else {
			
			session.setAttribute("message", "Check your email id !!");
			return "forgot_email_form_patient";
		}
		
	}
	
	@PostMapping("/send-otp-doctor-email")
	public String sendOTPDoctorEmail(@RequestParam("emailDoc") String emailDoc, HttpSession session) {
		
		System.out.println("Email id is :" + emailDoc);
		
		//generating otp for 4 digits
		int otp = random.nextInt(8888888);
		System.out.println("OTP "+ otp);
		
		
		//Write code for send OTP to email...
		String subject = "OTP From E-Doc";
		String message = "<div style='border:2px solid rgb(210, 210, 210); padding:20px; background-color:rgb(240, 240, 240)'>"
				+"<h3>"
				+"Your OTP is"
				+"<br>"
				+"" +otp
				+"</h3>"
				+"</div>"; 
		String to = emailDoc;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email", emailDoc);
			
			return "verify_otp_doctor";
			
		} else {
			
			session.setAttribute("message", "Check your email id !!");
			return "forgot_email_form_doctor";
		}
		
	}
	
	
	//verify-otp
	@PostMapping("/verify-otp-patient")
	private String veriftyOtpPatient(@RequestParam("otpPat") int otpPat, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpPat) {
			//Password change form
			System.out.println("Otp is correct for patient");
			PatientDetail patientDetail = this.patientRepository.findByPatientEmail(email);
			
			if(patientDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_patient";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_patient";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_patient";
		}
		
	}
	
	//verify-otp
	@PostMapping("/verify-otp-doctor")
	private String veriftyOtpDoctor(@RequestParam("otpDoc") int otpDoc, HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otpDoc) {
			//Password change form
			System.out.println("Otp is correct for doctor");
			DoctorDetail doctorDetail = this.doctorRepository.findByDoctorEmail(email);
			
			if(doctorDetail == null) {
				//Send error message
				session.setAttribute("message", "User does not exist with this email!");
				return "forgot_email_form_doctor";
				
			} else {
				//Send change password form
				
			}
			
			return "password_change_form_doctor";
		} else {
			
			session.setAttribute("message", "You have entered wrong OTP");
			return "verify_otp_doctor";
		}
		
	}
	
	
	
	//Change Password
	@PostMapping("/change-password-patient")
	private String changePasswordPatient(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		PatientDetail patientDetail = this.patientRepository.findByPatientEmail(email);
		patientDetail.setPatientPassword(this.bCryptPasswordEncoder.encode(newpassword));
		this.patientRepository.save(patientDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/patient/login";
	}
	
	@PostMapping("/change-password-doctor")
	private String changePasswordDoctor(@RequestParam("newpassword") String newpassword, 
			HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		DoctorDetail doctorDetail = this.doctorRepository.findByDoctorEmail(email);
		doctorDetail.setDoctorPassword(this.bCryptPasswordEncoder.encode(newpassword));
		this.doctorRepository.save(doctorDetail);
		session.setAttribute("changePass", "Your Passwrod is changed successfully.");
		return "redirect:/doctor/login";
	}

}
