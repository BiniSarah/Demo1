package com.restcall.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.cdi.ContextName;

@ContextName("restfulldemo")
public class TempDataBase {
	
	private static List<Employee> empList = new ArrayList<Employee>();
	
	static {
		empList.add(new Employee (1001, "xxx", "yyyy"));
		empList.add(new Employee (1002, "aaaa", "bbbb"));
		empList.add(new Employee (1003, "ccc", "ddddddd"));
		
		
	}
	
	public static List<Employee> getEmpList(){
		return empList;
	}
	
	public static void setEmpList(List<Employee> empList) {
		TempDataBase.empList=empList;
	}
}
