package org.wei.spring.jdbc.configuration;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.wei.spring.jdbc.domain.User;

@Component("userFactory")
public class UserFactoryBean implements FactoryBean<User>, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(UserFactoryBean.class);
	
	private User user;

	@Override
	public void afterPropertiesSet() throws Exception {
		user = new User();
		logger.info("Inside afterPropertiesSet() of UserFactoryBean");		
	}

	@Override
	public User getObject() throws Exception {
		user.setName("thisName" + new Random().nextInt(1000));
		logger.info("---> Inside getObject() of UserFactoryBean");
		return user;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	

}
