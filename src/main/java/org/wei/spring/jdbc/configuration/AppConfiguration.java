package org.wei.spring.jdbc.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Reference: 
// http://www.swiftmind.com/de/2011/06/22/spring-3-1-m2-testing-with-configuration-classes-and-profiles/
// http://krams915.blogspot.com/2012/12/spring-and-thymeleaf-with-javaconfig_8540.html
// http://www.javacodegeeks.com/2012/02/properties-with-spring.html

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages={"org.wei.spring.jdbc","org.wei.spring.jdbc.service"})
@ComponentScan(basePackages={"org.wei.spring"})
@PropertySource("classpath:conf/db-setting.conf")
@Profile("dev")
public class AppConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript(environment.getProperty("schema"))
				.addScript(environment.getProperty("data"))
				.build();			
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;	
	}
	
	@Bean(name="string")
	public String string() {
		return "TEST";
	}
	
	/*
	@Bean
	public IUserDao userDao() {
		UserJdbcTemplateDao dao = new UserJdbcTemplateDao();
		dao.setDataSource(devDataSource());
		return dao;
	}
	*/

}
