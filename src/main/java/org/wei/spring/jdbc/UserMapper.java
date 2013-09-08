package org.wei.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.wei.spring.jdbc.domain.User;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		user.setCity(rs.getString("city"));
		user.setState(rs.getString("state"));
		user.setPin(rs.getInt("pin"));
		return user;
	}
}
