package com.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Employee;
import com.task.repositry.EmployeeRepositry;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	@Autowired
	EmployeeRepositry employeeRepositry;
	

	@Override
	public void addStudent(Employee employee) {
		employeeRepositry.save(employee);
		
	}

	@Override
	public List<Employee> getTheListEmployee() {
		
		return employeeRepositry.findAll();
	}


	
	
}
