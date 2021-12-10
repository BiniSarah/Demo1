package com.restservices.camel.api.resources;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.restservices.camel.api.dto.Employee;
import com.restservices.camel.api.process.EmployeeProcessor;
import com.restservices.camel.api.service.EmployeeService;

@Component
public class ApplicationResources extends RouteBuilder {

	@Autowired
	private EmployeeService service;
	
	@BeanInject
	private EmployeeProcessor processor;
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);
		
		
		
	rest().get("/getEmpDetails").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(()->service.getEmpDetails()).endRest();
		
	rest().post("/addEmp").consumes(MediaType.APPLICATION_JSON_VALUE).type(Employee.class).outType(Employee.class).route().process(processor).endRest();
	
	rest().put("/updateEmp/id").consumes(MediaType.APPLICATION_JSON_VALUE).type(Employee.class).outType(Employee.class).route().process(processor).endRest();
		
	rest().get("/employee/{empno}").description("Details by id").outType(Employee.class)
	.produces(MediaType.APPLICATION_JSON_VALUE).route()
	 .bean(EmployeeService.class, "findById(${header.id})");
		
		
		
		
		//rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Welcome"));
	}

}
