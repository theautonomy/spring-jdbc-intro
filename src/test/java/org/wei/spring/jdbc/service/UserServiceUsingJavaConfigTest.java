package org.wei.spring.jdbc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.wei.spring.jdbc.IUserDao;
import org.wei.spring.jdbc.configuration.AppConfiguration;
import org.wei.spring.jdbc.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	    loader=AnnotationConfigContextLoader.class,
	    classes={
	        AppConfiguration.class /*,
	        OtherConfig.class,
	        JndiDataConfig.class */	        
	    })
@ActiveProfiles("dev")
public class UserServiceUsingJavaConfigTest {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Test
	public void testUpdateUsers() {		
		userService.updateUsers();
		User user = userDAO.selectUserByPin(102);
		assertEquals("New User 2",  user.getName());		
	}
	
	@Test
	public void testUpdateUsersFailed() {
		userService.updateUsers();
		User user = userDAO.selectUserByPin(102);
		assertEquals("New User 2",  user.getName());
		
		try {
			userService.updateUsersFailed(103);
		} catch  (Exception e) {
			
		}
		
		user = userDAO.selectUserByPin(102);
		assertEquals("New User 2",  user.getName());
	}

}