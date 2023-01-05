package com.task.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.task.model.Employee;

public class CsvFileGenerator {
	
	public void writeStudentsToCsv(List<Employee> employees, Writer writer) {
		try {

		CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
		for (Employee employee : employees) {
		printer.printRecord(employee.getId(), employee.getName(), employee.getActive(),
				employee.getImage(),employee.getCode());
		}
		} catch (IOException e) {
		e.printStackTrace();
		}
		}

}
