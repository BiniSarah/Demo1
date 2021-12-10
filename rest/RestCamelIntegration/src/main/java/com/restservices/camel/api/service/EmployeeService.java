package com.restservices.camel.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.restservices.camel.api.dto.Employee;

@Service
public class EmployeeService {

	private List<Employee> emp= new ArrayList<>();
	
	@PostConstruct
	public void initDB() {
		emp.add(new Employee(001,"Dhrishya","Developer"));
		emp.add(new Employee(002,"Ann","Tester"));
		emp.add(new Employee(003,"Feba","API security"));
		
	}
	
	//post
	public Employee addEmployee(Employee employee) {
		emp.add(employee);
		return employee;
	}
	
	//get
	public List<Employee> getEmpDetails(){
		return emp;
	}
	
	
	//by id
	public List<Employee> getEmpById(int empno){
		emp.get(empno);
		return emp;
	}
	
	
	
	//put
	public Employee updateEmployee(int id, Employee employee){  
		emp.set(id, employee);
	return employee;
		}
}
