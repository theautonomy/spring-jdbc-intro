package org.wei.spring.jdbc.dao.springjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;

//@Repository("userSpringJpaDao")
public class UserSpringJpaDao implements IUserDao {

	@Autowired
	private UserSpringJpaRepository userSpringJpaRepository;

	@Override
	public int getCount() {
		return (int)userSpringJpaRepository.count();
	}

	@Override
	public List<User> selectAllUsers() {
		//return userSpringJpaRepository.findAll();
		return userSpringJpaRepository.findAllUsers();
	}

	@Override
	public User selectUserByPin(int pin) {
		return userSpringJpaRepository.findByPin(pin);
		
		
	}

	@Override
	public int updateUserName(int pin, String name) {
		/*
		User user = selectUserByPin(pin);
		user.setName(name);
		userSpringJpaRepository.saveAndFlush(user);
		return 1;
		*/
		return userSpringJpaRepository.udpateUserByPin(name, pin);

	}

}
