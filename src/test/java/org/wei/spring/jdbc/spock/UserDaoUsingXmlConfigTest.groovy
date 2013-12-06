package org.wei.spring.jdbc.spock

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
class UserDaoUsingXmlConfigTest extends Specification  {
	
	@Autowired
	@Qualifier("userDao")
	private IUserDao userDAO;
	
	def "test select" () {		
		setup:
		
		when:
			def users = userDAO.selectAllUsers()
		
		then:
			users.size == 3
	}
}
