package org.wei.spring.jdbc.spock

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import org.wei.spring.jdbc.dao.IUserDao
import org.wei.spring.jdbc.domain.User;

import spock.lang.Specification

@ContextConfiguration(locations="classpath:applicationContext.xml")
@ActiveProfiles("dev")
class TestUserDao extends Specification  {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;

	@Autowired
	private PlatformTransactionManager txManager;
	
	def "test select" () {
		
		setup:
		
		when:
			def users = userDAO.selectAllUsers()
		
		then:
			users.size == 3
	}
}
