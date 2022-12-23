package com.edocapp.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String message, String to) {
		
		boolean f = false;
		String from = "umarshabbir5726@gmail.com";
		
//		//Variable for gmail
//		String host = "smtp.gmail.com";
//		
//		//get the system properties
//		Properties properties = System.getProperties();
//		System.out.println("PROPERTIES: " + properties);
//		
//		//Setting important information to properties object
//		//Host set
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		
//		//Step 1: to get the session object
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("umarshabbir5726@gmail.com", "atrijgmlibmongic");
//			}
//			
//		});
//		session.setDebug(true);
		
		
		//Step 2: compose the message [text,multi media]
		MimeMessage m =mailSender.createMimeMessage();
		
		try {
			//From email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
//			m.setText(message);
			m.setContent(message, "text/html");
			
			//send
			mailSender.send(m);
			System.out.println("mail send and in service class");
			
			//send the message using transport class
			
			System.out.println("Send email success...");
			
			f = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
}
