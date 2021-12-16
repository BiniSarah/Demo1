package com.restcall.demo;

import java.util.List;

import javax.inject.Named;

import org.apache.camel.Header;
import org.apache.camel.cdi.ContextName;

@ContextName("restfulldemo")
@Named("employeDAO")
public class EmployeeDAO {
	
	public List<Employee> getAllEmps(){
		return TempDataBase.getEmpList();
	}

	
	public Employee getEmpById(@Header("empNo") int rno) {
		
		System.out.println("Fetching employee info of rno:" +rno);
		return TempDataBase.getEmpList().get(0);
		
	}
	
	public String saveEmployee(Employee e) {
		TempDataBase.getEmpList().add(e);
		return " Employee successfully saved";
	}
	
}
