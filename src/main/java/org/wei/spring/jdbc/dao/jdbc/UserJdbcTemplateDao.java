package org.wei.spring.jdbc.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;

@Repository("userDao")
public class UserJdbcTemplateDao implements IUserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public UserJdbcTemplateDao() {

	}

	public List<User> selectAllUsers() {
		return jdbcTemplate.query("select * from USER order by pin", new UserMapper());
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from USER",
				Integer.class).intValue();
	}

	public User selectUserByPin(int pin) {
		return jdbcTemplate.queryForObject("select * from user where pin = ?",
				new UserMapper(), new Object[] { Integer.valueOf(pin) });
	}

	public int updateUserName(int pin, String name) {
		return jdbcTemplate.update("update user set name = ? where pin = ?",
				name, Integer.valueOf(pin));
	}

	@Override
	public User insertUser(User user) {
		jdbcTemplate.update("insert into user (name, address, city, state, pin) values (?, ?, ?, ?, ?)", 
				new Object [] {user.getName(), user.getAddress(), user.getCity(), user.getState(), user.getPin()});
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		jdbcTemplate.update("delete from user where id = ?", id);
	}

}