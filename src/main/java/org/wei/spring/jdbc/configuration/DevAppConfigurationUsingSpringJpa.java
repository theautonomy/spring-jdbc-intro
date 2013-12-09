package org.wei.spring.jdbc.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.wei.spring.jdbc.dao.springjpa.UserSpringJpaDao;


@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = { "org.wei.spring.jdbc" })
@PropertySource("classpath:conf/dev/db-setting.conf")
@Profile("devJpa")
public class DevAppConfigurationUsingSpringJpa extends AppConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

		emfb.setDataSource(this.dataSource());
		emfb.setPackagesToScan(new String[] { "org.wei.spring.jdbc" });
		emfb.setPersistenceUnitName("MyPU");

		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		emfb.setJpaVendorAdapter(va);

		Properties ps = new Properties();
		ps.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		emfb.setJpaProperties(ps);

		emfb.afterPropertiesSet();

		return emfb;

	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return tm;
	}
	
	@Bean(name="userSpringJpaDao")
	public UserSpringJpaDao userSpringJpaDao() {
		return new UserSpringJpaDao();
	}

}
