package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RouterMasterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RouterMasterApplication.class, args);
//		NeoClient neoClient = context.getBean(NeoClient.class);
//	    System.out.println(">> message = " + neoClient.setMessage().block());
	}

}
