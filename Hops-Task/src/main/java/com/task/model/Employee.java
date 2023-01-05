package com.task.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employee")
public class Employee {
	
	@Id
	private String id;
	private String name;
	private String active;
	private String image;
	private int code;
	
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", active=" + active + ", image=" + image + ", code=" + code
				+ "]";
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	public Employee(String id, String name, String active, String image, int code) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.image = image;
		this.code = code;
	}
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
