package org.wei.spring.jdbc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wei.spring.jdbc.domain.User;

@Configuration
public class AnotherAppConfiguration {
	
	@Autowired
	@Qualifier("userFactory")
	private UserFactoryBean userFactoryBean;

	/*
	@Bean
	public User user() {
		User user =  new User();
		user.setName("wei");
		user.setPin(4321);
		return user;
	}
	*/
	
	/*
	@Bean
	public UserFactoryBean userFactoryBean() {
		return new UserFactoryBean();
	}
	*/
	
	@Bean(name="myUser")
	public User user() throws Exception {
		return userFactoryBean.getObject();
	}
	
}
