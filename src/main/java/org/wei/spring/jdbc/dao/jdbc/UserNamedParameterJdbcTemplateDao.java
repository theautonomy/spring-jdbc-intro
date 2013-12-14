package org.wei.spring.jdbc.dao.jdbc;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.wei.spring.jdbc.dao.IUserDao;
import org.wei.spring.jdbc.domain.User;

@Repository("userDaoUsingNP")
public class UserNamedParameterJdbcTemplateDao implements IUserDao {

	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getCount() {
		Map<String, ?> namedParameters = null;
		return jdbcTemplate.queryForObject
				("SELECT COUNT(*) FROM USER", namedParameters, Integer.class);
	}

	public List<User> selectAllUsers() {
		return jdbcTemplate.query("SELECT * FROM USER ORDER BY PIN", new UserMapper());
	}

	public User selectUserByPin(int pin) {
		Map<String, Integer> namedParameters = Collections.singletonMap("pin", pin);
		return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE PIN =  :pin", namedParameters, new UserMapper());
	}

	public int updateUserName(int pin, String name) {
		/*
		SqlParameterSource namedParameters = new MapSqlParameterSource("name", name).addValue("pin",  pin);
		return jdbcTemplate.update("UPDATE USER SET NAME = :name WHERE PIN = :pin", namedParameters);
		*/		
		User user = new User();
		user.setPin(102);
		user.setName(name);
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update("UPDATE USER SET NAME= :name WHERE PIN = :pin", namedParameters);
	}

	@Override
	public User insertUser(User user) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update("INSERT INTO USER (NAME, ADDRESS, CITY, STATE, PIN) " +
				"VALUES (:name, :address, :city, :state, :pin)", namedParameters);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		Map<String, Long> namedParameters = Collections.singletonMap("id", id);
		jdbcTemplate.update("DELETE FROM USER WHERE ID = :id", namedParameters);
	}

}
