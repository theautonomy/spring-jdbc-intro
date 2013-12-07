package org.wei.spring.jdbc.spock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.wei.spring.jdbc.configuration.DevAppConfiguration
import org.wei.spring.jdbc.dao.IUserDao

import spock.lang.Specification

@ContextConfiguration(
	    loader=AnnotationConfigContextLoader.class,
	    classes=[
	        DevAppConfiguration.class
	    ])
@ActiveProfiles("dev")
class UserDaoUsingJavaConfigTest extends Specification  {
	
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
	
	def "test select 2" () {	
		setup:
		
		expect:
			userDAO.selectAllUsers().size == 3
	}
	
}
