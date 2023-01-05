package com.task.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.task.model.Employee;


@Repository
public interface EmployeeRepositry extends MongoRepository<Employee, String> {


}
