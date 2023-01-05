package com.task.controller;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.task.helper.FileUploadHelper;




@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class FileUplloadController {

	
	@Autowired 
	private FileUploadHelper fileUploadHelper;
	
	
	
	@PostMapping("/uplode-file")
	public ResponseEntity<String>uplodFile(@RequestParam ("file") MultipartFile file)	
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		
		
		try {
			
			//validation 
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file must be required");
			}
			
			//
//			if(file.getContentType().equals("image/jpg")) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jped file allow");
//			}
			
			
			//file upload data 
			boolean f= fileUploadHelper.uploadFile(file);
			if(f) 
			{
				//return ResponseEntity.ok("file is upload success");
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}
			}
			catch (Exception e) {
					e.printStackTrace();
			}
		
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
	}
	
	
}
