package org.wei.spring.jdbc.main;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.wei.spring.jdbc.configuration.DevAppConfiguration;
import org.wei.spring.jdbc.configuration.ProductionAppConfiguration;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;
import org.wei.spring.jdbc.service.IUserService;

@Component("main")
public class MainWithJavaConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MainWithJavaConfig.class);

	@Autowired
	@Qualifier("userService")
	IUserService userService;

	@Autowired
	@Qualifier("userDao")
	IUserDao userDao;

	public void doSomething() {
		User user = userDao.selectUserByPin(102);
		logger.info("User Name=" + user.getName());
		
		userService.updateUsers();
	}

	public static void main(String[] args) throws SQLException {
		//System.setProperty("spring.profiles.active", "dev");
		System.setProperty("spring.profiles.active", "production,aspect");
		
		String activeProfile = System.getProperty("spring.profiles.active");
		
		AnnotationConfigApplicationContext context = null;
		
		if (activeProfile.matches(".*dev.*")) {
			context = new AnnotationConfigApplicationContext(DevAppConfiguration.class);
		} else if (activeProfile.matches(".*production.*")) {
			context = new AnnotationConfigApplicationContext(ProductionAppConfiguration.class);
		}
		
		MainWithJavaConfig thisMain = (MainWithJavaConfig) context.getBean("main");
		thisMain.doSomething();

		IUserDao userDao = (IUserDao) context.getBean("userDao");
		User user = userDao.selectUserByPin(102);
		logger.info("User Name=" + user.getName());

		user = (User)context.getBean("user");
		logger.info("User configured in java configuraton: " + user.getName());
		
		context.close();
	}

}
