package org.wei.spring.jdbc.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wei.spring.jdbc.dao.IUserDao;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;

	@Transactional
	public void updateUsers(String s1, int i1) {
		userDAO.updateUserName(101, "New User1");
		userDAO.updateUserName(102, "New User2");
		
		// Wait some time for AOP monitoring aspect demonstration purpose
		try {
			Thread.sleep(new Random().nextInt(3) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

	@Transactional
	// Optionally, you can pass a value to @Transactional annotation. 
	// Spring looks for any bean declared in the context with the name “transactionManager”.
	public void updateUsersFailed(int i) {
		userDAO.updateUserName(102, "New User2");

		if (i == 103) {
			throw new RuntimeException("someting went wrong");
		}
		
		userDAO.updateUserName(103, "New User3");
	}

}
