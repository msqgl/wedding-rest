package com.msqgl.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:rest.properties"})
public class DBConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
    dataSource.setUrl(env.getRequiredProperty("db.url"));
    dataSource.setUsername(env.getRequiredProperty("db.user"));
    dataSource.setPassword(env.getRequiredProperty("db.password"));
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.setResultsMapCaseInsensitive(true);
    return jdbcTemplate;
  }
}
