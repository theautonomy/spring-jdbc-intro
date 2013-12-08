package org.wei.spring.jdbc.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public enum EnvironmentEnum {

	DEV {
		@Override
		public GenericApplicationContext loadApplicationContext() {
			return new AnnotationConfigApplicationContext(
					DevAppConfiguration.class);
		}
	},

	PRODUCTION {
		@Override
		public GenericApplicationContext loadApplicationContext() {
			return new AnnotationConfigApplicationContext(
					ProductionAppConfiguration.class);
		}
	};

	public static EnvironmentEnum fromString(String profile) {
		EnvironmentEnum env = EnvironmentEnum.DEV;
		String[] profiles = profile.toUpperCase().split(" *, *");
		List<String> profileList = Arrays.asList(profiles);

		for (EnvironmentEnum thisEnv : values()) {
			String type = thisEnv.toString();
			if (profileList.contains(type)) {
				env = thisEnv;
				break;
			}
		}

		return env;
	}

	public abstract GenericApplicationContext loadApplicationContext();

}
