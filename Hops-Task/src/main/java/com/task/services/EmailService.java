package com.task.services;



import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class EmailService  {
	
	 Random random = new Random();
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	
	@Value("${spring.mail.username}")
	private String email1;
	
	public  int otp;
	
	public int getotp;
	
	public int sendOtp() {
		
		int otpfromrandom =random.nextInt(10000);	
		otp=otpfromrandom;

		return otp;
			
		}

	public void sendMailTest(String sendemail) {
		try {
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED , StandardCharsets.UTF_8.name()); 
			
			helper.setFrom(email1);
			helper.setTo(sendemail);
			helper.setSubject("hello sagar");
			// System.out.println("mail method otp Service mese :" + otp);
			helper.setText("Testing api " + otp);
			
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendMail(String email) {
		try {
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED , StandardCharsets.UTF_8.name()); 
			
			helper.setFrom(email1);
			helper.setTo(email);
			helper.setSubject("hello sagar");
			helper.setText("Testing api " + otp);
			
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void otpVerification(int sendOtp) {
		
		 System.out.println("verify otp Service mese :" + otp);
		 try {
			if(otp == sendOtp)
			{
				System.out.println(sendOtp);
				System.out.println("otp verify by mail");
				
			}else {
				System.out.println("otp is incroect");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getotp() {
		System.out.println("get OTP = 00000: " +otp);
		return otp;
	}

	
	}
	

	
	
	
	
	
	

