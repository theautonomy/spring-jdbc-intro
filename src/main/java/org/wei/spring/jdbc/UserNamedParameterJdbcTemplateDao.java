package org.wei.spring.jdbc;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.wei.spring.jdbc.domain.User;

@Repository
@Qualifier("userDaoUsingNP")
public class UserNamedParameterJdbcTemplateDao implements IUserDao {

	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getCount() {
		Map<String, ?> namedParameters = null;
		return jdbcTemplate.queryForObject
				("select count(*) from USER", namedParameters, Integer.class);
	}

	public List<User> selectAllUsers() {
		return jdbcTemplate.query("select * from USER", new UserMapper());
	}

	public User selectUserByPin(int pin) {
	
		Map<String, Integer> namedParameters = Collections.singletonMap("pin", pin);
		return jdbcTemplate.queryForObject("select * from user where pin =  :pin", namedParameters, new UserMapper());

		
	}

	public int updateUserName(int pin, String name) {
		/*
		SqlParameterSource namedParameters = new MapSqlParameterSource("name", name).addValue("pin",  pin);
		return jdbcTemplate.update("update user set name = :name where pin = :pin", namedParameters);
		*/
		
		User user = new User();
		user.setPin(102);
		user.setName(name);
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update("update user set name= :name where pin = :pin", namedParameters);
	}

}
