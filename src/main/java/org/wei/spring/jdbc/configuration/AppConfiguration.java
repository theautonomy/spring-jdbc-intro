package org.wei.spring.jdbc.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
//@ComponentScan(basePackages={"org.wei.spring.jdbc","org.wei.spring.jdbc.service"})
@ComponentScan(basePackages={"org.wei.spring.jdbc"})
@PropertySource("classpath:conf/db-setting.conf")
@Import(AnotherAppConfiguration.class)
//@ImportResource("classpath:myconfig.xml")
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
	
	/*
	@Bean
	public IUserDao userDao() {
		UserJdbcTemplateDao dao = new UserJdbcTemplateDao();
		dao.setDataSource(devDataSource());
		return dao;
	}
	*/

}
