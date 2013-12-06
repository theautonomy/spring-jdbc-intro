package org.wei.spring.jdbc.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;
import org.wei.spring.jdbc.service.IUserService;

@Component
public class MainWithXmlConfig {
	
	private static Logger logger = LoggerFactory.getLogger(MainWithXmlConfig.class);

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
		System.setProperty("spring.profiles.active", "dev");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		DataSource ds = (DataSource) context.getBean("dataSource");
		logger.info("{}", ds.getLoginTimeout());
		
		MainWithXmlConfig thisMain = (MainWithXmlConfig) context.getBean(MainWithXmlConfig.class);
		thisMain.doSomething();

		
		IUserDao userDao = (IUserDao) context.getBean("userDao");
		User user = userDao.selectUserByPin(102);
		logger.info("User Name=" + user.getName());

		context.close();
	}

}
