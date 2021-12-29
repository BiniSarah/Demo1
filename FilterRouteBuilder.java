package com.camel.demo;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FilterRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {
		from("file:src/data_Filter?noop=true")
		.filter(xpath("/order[not(@test)]"))
		.to("jms:incomingOrders");
		
		from("jms:incomingOrders")
		//.filter(xpath("/order[not(@test)]"))
		.process(new Processor() {

			public void process(Exchange exchange) throws Exception {
			//System.out.println("context is" + exchange.getIn().getBody(String.class));	
			System.out.println("context is  :" + exchange.getIn().getHeader("CamelFileName"));
			}
			
		});
	}
	public static void main(String[] args) {
		CamelContext context=new DefaultCamelContext();
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("vm://localhost");
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		try {
			context.addRoutes(new FilterRouteBuilder());
			context.start();
			Thread.sleep(1000 * 5);
			context.stop();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
