package com.stud.application;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.stud")
@EnableJpaRepositories(basePackages = "com.stud")
@EntityScan("com.stud")
public class StudentApplication {
	private static final Logger LOG = Logger.getLogger(StudentApplication.class.getName());

	public static void main(String[] args) {
		
		SpringApplication.run(StudentApplication.class, args);
	}

}
