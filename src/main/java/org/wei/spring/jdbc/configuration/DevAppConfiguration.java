package org.wei.spring.jdbc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
//@EnableJpaRepositories(basePackages={"org.wei.spring.jdbc"})
@PropertySource("classpath:conf/dev/db-setting.conf")
@Profile("dev")
public class DevAppConfiguration extends AppConfiguration {
	
}
