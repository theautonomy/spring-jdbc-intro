package org.wei.spring.jdbc.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		return jdbcTemplate.query("SELECT * FROM USER ORDER BY PIN", new UserMapper());
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER",
				Integer.class).intValue();
	}

	public User selectUserByPin(int pin) {
		return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE PIN = ?",
				new UserMapper(), new Object[] { Integer.valueOf(pin) });
	}

	public int updateUserName(final int pin, final String name) {
		return jdbcTemplate.update("UPDATE USER SET NAME = ? WHERE PIN = ?", name, Integer.valueOf(pin));
	}

	public User insertUser(final User user) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
	    jdbcTemplate.update(new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(
	                	"INSERT INTO USER (NAME, ADDRESS, CITY, STATE, PIN) VALUES (?, ?, ?, ?, ?)", new String [] {"ID"});
	                ps.setString(1, user.getName());
	                ps.setString(2, user.getAddress());
	                ps.setString(3, user.getCity());
	                ps.setString(4, user.getState());
	                ps.setInt(5, Integer.valueOf(user.getPin()));
	                return ps;
	            }
	        }, keyHolder);

	    user.setId(keyHolder.getKey().longValue());
	    return user;
		
		/*
		jdbcTemplate.update("INSERT INTO USER (NAME, ADDRESS, CITY, STATE, PIN) VALUES (?, ?, ?, ?, ?)", 
				new Object [] {user.getName(), user.getAddress(), user.getCity(), user.getState(), user.getPin()});
		return user;
		*/
	}

	public void deleteUser(Long id) {
		jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", id);
	}

}