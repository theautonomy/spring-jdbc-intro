package org.wei.spring.jdbc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@ActiveProfiles("dev")
public class UserServiceTest {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Test
	public void testUpdateUsers() {	
		userService.updateUsers("dummy", 1);
		
		User user = userDAO.selectUserByPin(101);
		assertEquals("New User1",  user.getName());		
		
		user = userDAO.selectUserByPin(102);
		assertEquals("New User2",  user.getName());		
	}
	
	@Test
	public void testUpdateUsersFailed() {
		
		try {
			userService.updateUsersFailed(103);
		} catch  (Exception e) {
			
		}
		
		User user = userDAO.selectUserByPin(102);
		assertEquals("User2 - DEV",  user.getName());
		
		user = userDAO.selectUserByPin(103);
		assertEquals("User3 - DEV",  user.getName());
	}

}
