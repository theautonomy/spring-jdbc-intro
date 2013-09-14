package org.wei.spring.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wei.spring.jdbc.dao.IUserDao;

@Service("userService")
// @Qualifier("userService")
public class UserService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;

	@Transactional
	public void updateUsers() {
		userDAO.updateUserName(102, "New User 2");
		userDAO.updateUserName(101, "New User 1");
	}

	@Transactional
	public void updateUsersFailed(int i) {
		userDAO.updateUserName(102, "New User 2 new");

		if (i == 103) {
			throw new RuntimeException("someting went wrong");
		}
		userDAO.updateUserName(101, "New User 1");
	}

}
