package com.task.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Employee;
import com.task.model.User;
import com.task.repositry.UserRepositry;
import com.task.services.EmailService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {

	Random random = new Random(1000);

	@Autowired
	private UserRepositry userRepositry;

	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User save = this.userRepositry.save(user);
		return ResponseEntity.ok(user);

	}

	

	// get all user
	@GetMapping("/alluser")
	public List<User> getAllUser() {
		return userRepositry.findAll();
	}
	

	// login user by email or password
	@PostMapping("/login")
	public ResponseEntity<HttpStatus> loginpage(@RequestBody User user) {
		User userlogin = userRepositry.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (user.getEmail().equals(userlogin.getEmail()) && user.getPassword().equals(userlogin.getPassword())) {
			
			
			userlogin.setOtp(emailService.sendOtp());
			emailService.sendMailTest(user.getEmail());
			this.userRepositry.save(userlogin);
			
			System.out.println("email and password susssetes full");
			
			return ResponseEntity.ok(HttpStatus.ACCEPTED);

		} else {
			System.out.println("email password wrong");
			return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/sendotp/{email}")
	public ResponseEntity<HttpStatus> sendemail(@PathVariable String email) {
		
		emailService.sendOtp();
		emailService.sendMail (email);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
	

	
	
	@GetMapping("/votp/{otp}")
	public int verifyOtp(@PathVariable int otp) {

		int sendOtp = emailService.getotp();
		System.out.println(sendOtp);

		if (otp == sendOtp) {
			
			System.out.println(sendOtp);
			return sendOtp;

		} else {

			return 111;

		}
                 
	}
	
//	
//	@PostMapping("/getByEmail/{email}/{otp}")
//	public ResponseEntity<?> getByEmail(@PathVariable User user,@PathVariable String email,@PathVariable int otp){
//		User emailsetotp =userRepositry.findByEmail(user.getEmail());
//		User user1=userRepositry.findByotp(otp);
//		return ResponseEntity.ok(HttpStatus.ACCEPTED);
//	}
		

}
