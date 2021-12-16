package com.restcall.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.model.rest.RestBindingMode;

public class EmployeeRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration().host("localhost").port("8080")
		.component("servlet").bindingMode(RestBindingMode.json).jsonDataFormat("json-jackson")
		.endpointProperty("servletName", "CamelServlet");
	
		
		rest("/empservice/")
		.get().to("direct:getAll")
		
		.get("{empNo}").to("direct:getEmpById")
		
		.post().type(Employee.class).to("direct:saveEmp")
		
		
		
		
		
		from("direct:getAll").bean("employeDAO", "getAllEmps")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).endRest();
		
		
		from("direct:getEmpById").bean("employeDAO","getEmpById")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).endRest();
		
		
		from("direct:saveEmp").bean("employeDAO", "saveEmployee")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).endRest();
		
		
		
	
	
	
	}
	
	

}
