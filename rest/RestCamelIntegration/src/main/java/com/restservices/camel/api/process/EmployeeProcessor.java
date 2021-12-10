package com.restservices.camel.api.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restservices.camel.api.dto.Employee;
import com.restservices.camel.api.service.EmployeeService;
@Component
public class EmployeeProcessor implements Processor {
	@Autowired
	private EmployeeService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		service.addEmployee(exchange.getIn().getBody(Employee.class));
		//service.updateEmployee(0, exchange.getIn().getBody(Employee.class));
	
	}
	

}
