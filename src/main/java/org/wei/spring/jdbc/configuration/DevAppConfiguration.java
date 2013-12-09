package org.wei.spring.jdbc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:conf/dev/db-setting.conf")
@Profile("dev")
public class DevAppConfiguration extends AppConfiguration {
	
}
