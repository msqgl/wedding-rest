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
    final String driver = env.getRequiredProperty("db.driver");
    final String url = env.getRequiredProperty("db.url");
    final String database = env.getRequiredProperty("db.database");
    final String username = env.getRequiredProperty("db.user");
    final String password = env.getRequiredProperty("db.password");
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url + "/" + database);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.setResultsMapCaseInsensitive(true);
    return jdbcTemplate;
  }
}
