package com.task.controller;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.task.helper.FileUploadHelper;
import com.task.model.Employee;
import com.task.repositry.EmployeeRepositry;
import com.task.services.EmployeeService;
import com.task.util.ExcelGenerator;
import com.task.util.PdfGenerator;
import com.task.util.CsvFileGenerator;

@RestController
@RequestMapping("employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	@Autowired
	EmployeeRepositry employeeRepositry;
	@Autowired 
	FileUploadHelper fileUploadHelper;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	EmployeeService employeeService;
	

	
	//add emp
		@PostMapping("/add-emp")
		public ResponseEntity<?> addEmployee(@RequestParam ("employee") String employee,@RequestParam ("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
			
			
			Employee readvalue =mapper.readValue(employee, Employee.class);
			
			

			try {
				
				//validation 
				if(file.isEmpty()) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file must be required");
				}
				
				
				//file upload data 
				boolean f= fileUploadHelper.uploadFile(file);
				if(f) 
				{
					//return ResponseEntity.ok("file is upload success");
					String image_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString();
					 System.out.println("Iamge Url :" + image_url);
					 
					 readvalue.setImage(image_url);
					 
					 employeeRepositry.save(readvalue);
					 System.out.println(readvalue);
					 
					 
					 return ResponseEntity.status(HttpStatus.CREATED).body("saved data");
				}
				}
				catch (Exception e) {
						e.printStackTrace();
				}
			
			
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
			

		}
	
	//get all emp
	@GetMapping("/all")
	public List<Employee> getAll(){
		return employeeRepositry.findAll();
	}
	
	//get employee by id 
	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id){
		Employee employeeget =employeeRepositry.findById(id).orElseThrow();
		return ResponseEntity.ok(employeeget);
	}	
	
	//update emp
	@PostMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestParam ("employee") String employee,@RequestParam ("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		
		
		Employee readvalue =mapper.readValue(employee, Employee.class);
		
		

		try {
			
			//validation 
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file must be required");
			}
			
			
			//file upload data 
			boolean f= fileUploadHelper.uploadFile(file);
			if(f) 
			{
				//return ResponseEntity.ok("file is upload success");
				String image_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString();
				 System.out.println("Iamge Url :" + image_url);
				 
				 readvalue.setImage(image_url);
				 
				 employeeRepositry.save(readvalue);
				 System.out.println(readvalue);
				 
				 
				 return ResponseEntity.status(HttpStatus.CREATED).body("saved data");
			}
			}
			catch (Exception e) {
					e.printStackTrace();
			}
		
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
		

	}
	
	//delete
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteemployee(@PathVariable String id){
		Employee employee=employeeRepositry.findById(id).orElseThrow();
		employeeRepositry.delete(employee);
		Map<String, Boolean>responce=new HashMap<>();
		responce.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(responce);
	}
	
	
	@GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Employee> listOfStudents = employeeService.getTheListEmployee();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents);
        generator.generateExcelFile(response);
    }
	
	
	 @GetMapping("/export-to-pdf")
	  public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException 
	  {
	    response.setContentType("application/pdf");
	    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
	    String currentDateTime = dateFormat.format(new Date());
	    String headerkey = "Content-Disposition";
	    String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
	    response.setHeader(headerkey, headervalue);
	    List < Employee > listofStudents = employeeService.getTheListEmployee();
	    PdfGenerator generator = new PdfGenerator();
	    generator.generate(listofStudents, response);
	  }
	 
	 
	 
	 @GetMapping("/export-to-csv")
	  public void exportIntoCSV(HttpServletResponse response) throws IOException {
	    response.setContentType("text/csv");
	    response.addHeader("Content-Disposition", "attachment; filename=\"employee.csv\"");
	    CsvFileGenerator csvFileGenerator = new CsvFileGenerator();
		csvFileGenerator.writeStudentsToCsv(employeeService.getTheListEmployee(), response.getWriter());
	  }
}
	
	
	
	


	
	


