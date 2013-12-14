package org.wei.spring.jdbc.dao;

import java.util.List;

import org.wei.spring.jdbc.domain.User;

public interface IUserDao {
	
	public int getCount();
	public List<User> selectAllUsers();
	public User selectUserByPin(int pin);
	public int updateUserName(int pin, String name);
	
	public User insertUser(User user);
	
	public void deleteUser(Long id);
	
}
