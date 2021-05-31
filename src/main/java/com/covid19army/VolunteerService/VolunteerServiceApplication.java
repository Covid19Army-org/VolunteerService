package com.covid19army.VolunteerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.covid19army.core.extensions.HttpServletRequestExtension;
import com.covid19army.core.mex.rabbitmq.RabbitMQSender;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VolunteerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolunteerServiceApplication.class, args);
	}
	
	@Bean
	public HttpServletRequestExtension httpServletRequestExtension() {
		return new HttpServletRequestExtension();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public RabbitMQSender otpExchangeSender(
			@Value("${covid19army.rabbitmq.mobileotpexchange}") final String otpexchange,
			@Value("${covid19army.rabbitmq.mobileotpexchange.routingkey:}") final String routingkey) {
		return new RabbitMQSender(otpexchange, routingkey);
		
	}

}
