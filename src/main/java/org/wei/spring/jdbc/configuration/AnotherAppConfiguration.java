package org.wei.spring.jdbc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wei.spring.jdbc.domain.User;

@Configuration
public class AnotherAppConfiguration {

	@Bean
	public User user() {
		User user =  new User();
		user.setName("wei");
		user.setPin(4321);
		return user;
	}
	
}
