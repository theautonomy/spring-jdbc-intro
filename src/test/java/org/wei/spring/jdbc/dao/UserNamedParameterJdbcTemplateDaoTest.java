package org.wei.spring.jdbc.dao;

import static org.junit.Assert.*;

import java.util.List;

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
public class UserNamedParameterJdbcTemplateDaoTest {

	@Autowired
	@Qualifier("userDaoUsingNP")
	private IUserDao userDAO;

	@Test
	public void testSelectCount() {
		int count = userDAO.getCount();
		assertEquals(3,  count);
	}

	@Test
	public void testSelectAllUsers() {
		List<User> users = userDAO.selectAllUsers();
		assertEquals(3, users.size());
		assertEquals(102, users.get(1).getPin());
	}
	
	@Test
	public void testSelectUserByPin() {
		User user = userDAO.selectUserByPin(101);	
		assertEquals("Address 1", user.getAddress());
	}
	
	@Test
	public void testUpdateUserByPin() {
		int i = userDAO.updateUserName(102,  "New User");
		assertEquals(1,  i);
		
		User user = userDAO.selectUserByPin(102);
		assertEquals("New User",  user.getName());
	}
	
	@Test
	public void testInsertUser() {
		int count = userDAO.getCount();
		assertEquals(3, count);
		
		User user = new User();
		user.setName("Add a new user");
		user.setPin(110);
		userDAO.insertUser(user);
		
		count = userDAO.getCount();
		assertEquals(4, count);
		
		printUsers();
		
		user = userDAO.selectUserByPin(user.getPin());
	
		userDAO.deleteUser(user.getId());
		
		printUsers();
	}
	
	private void printUsers() {
		List<User> users = userDAO.selectAllUsers();
		for(User thisUser : users) {
			printUser(thisUser);
		}
	}
	
	private void printUser(User user) {
		System.out.println(user);
	}
	
}

