package com.notnull.dependent.dependentnotnull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class DependentNotnullApplication {

	@Autowired
	private ValidationService service;

	@Configuration
	public static class AppConfiguration {
		@Bean
		public Validator validatorFactory() {
			return new LocalValidatorFactoryBean();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DependentNotnullApplication.class, args);

	}

	@PostConstruct
	public void init() {
		User user1 = new User();

		user1.setUserId(1);
		user1.setFirstName("Jack");
		user1.setLastName("Johnson");
		user1.setIsPremium(false);

		User user2 = new User();

		user2.setUserId(2);
		user2.setFirstName("John");
		user2.setLastName("Jackson");
		user2.setIsPremium(true);

		User user3 = new User();

		user3.setUserId(3);
		user3.setFirstName("George");
		user3.setIsPremium(true);
		user3.setAmount(BigDecimal.TEN);

		List<User> users = Arrays.asList(user1, user2, user3);
		
		for (User user : users) {
			Errors errors = service.validation(user);
			System.out.println(errors);
		}
	}

}
