package org.netsharp.rest.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages={"org.netsharp"})
@ComponentScan(basePackages={"org.netsharp"})
public class RestAppApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RestAppApplication.class, args);
		
	}
	
}
