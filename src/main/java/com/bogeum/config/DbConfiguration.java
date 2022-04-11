package com.bogeum.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DbConfiguration {
	
	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() {
		return new HikariDataSource();
	}
	
}
