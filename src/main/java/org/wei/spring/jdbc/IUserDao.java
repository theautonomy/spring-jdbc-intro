package org.wei.spring.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wei.spring.jdbc.domain.User;

@Repository
public interface IUserDao {
	public int getCount();
	public List<User> selectAllUsers();
	public User selectUserByPin(int pin);
	public int updateUserName(int pin, String name);
}
