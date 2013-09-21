package org.wei.spring.jdbc.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.wei.spring.jdbc.configuration.AppConfiguration;
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
	}

	public static void main(String[] args) throws SQLException {
		System.setProperty("spring.profiles.active", "dev");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		// context.getEnvironment().setActiveProfiles("dev");
	
		// Second option to load context. The key is to set the profile correctly
		// AnnotationConfigApplicationContext context = new
		// AnnotationConfigApplicationContext();
		//
		// System.setProperty("spring.profiles.active", "dev");  or
		// context.getEnvironment().setActiveProfiles("dev");
		//
		// context.scan("org.wei.spring");
		// context.register(AppConfiguration.class);
		// context.refresh();

		DataSource ds = (DataSource) context.getBean("dataSource");
		logger.info("{}", ds.getLoginTimeout());
		logger.info("logging");
		
		MainWithJavaConfig thisMain = (MainWithJavaConfig) context.getBean("main");
		thisMain.doSomething();

		
		IUserDao userDao = (IUserDao) context.getBean("userDao");
		User user = userDao.selectUserByPin(102);
		logger.info("User Name=" + user.getName());

		context.close();
	}

}
