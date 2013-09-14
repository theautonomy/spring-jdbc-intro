package org.wei.spring.jdbc.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.wei.spring.jdbc.configuration.AppConfiguration;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;
import org.wei.spring.jdbc.service.IUserService;

// @ActiveProfiles("dev") - this doesn't work in non unit test class
@Component
public class Main {

	@Autowired
	@Qualifier("userService")
	IUserService userService;

	@Autowired
	@Qualifier("userDao")
	IUserDao userDao;

	public void doSomething() {
		User user = userDao.selectUserByPin(102);
		System.out.println("User Name=" + user.getName());
	}

	public static void main(String[] args) throws SQLException {
		System.setProperty("spring.profiles.active", "dev");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		//context.getEnvironment().setActiveProfiles("dev");
		

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//		// System.setProperty("spring.profiles.active", "dev");
//		context.getEnvironment().setActiveProfiles("dev");
//
//		context.scan("org.wei.spring");
//		context.register(AppConfiguration.class);
//		context.refresh();

		String test = context.getBean("string", String.class);
		System.out.println("test=" + test);

		DataSource ds = (DataSource) context.getBean("dataSource");
		System.out.println(ds.getLoginTimeout());

		Main thisMain = (Main) context.getBean("main");
		thisMain.doSomething();

		IUserDao userDao = (IUserDao)context.getBean("userDao");
		User user = userDao.selectUserByPin(102);
		System.out.println("User Name=" + user.getName());

		context.close();
	}
	
}
