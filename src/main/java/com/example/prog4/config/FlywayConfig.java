package com.example.prog4.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class FlywayConfig {
  private final Environment environment;

  @Bean(initMethod = "migrate")
  public Flyway flywayDb1() {
    Flyway flyway = Flyway.configure()
            .dataSource(DataSourceBuilder.create()
                    .url(environment.getProperty("spring.datasource.url"))
                    .username(environment.getProperty("spring.datasource.username"))
                    .password(environment.getProperty("spring.datasource.password"))
                    .build())
            .locations("classpath:db/migration/employee_db")
            .load();
    flyway.baseline();
    return flyway;
  }

  @Bean(initMethod = "migrate")
  public Flyway flywayDb2() {
    Flyway flyway = Flyway.configure()
            .dataSource(DataSourceBuilder.create()
                    .url(environment.getProperty("spring.second-datasource.url"))
                    .username(environment.getProperty("spring.second-datasource.username"))
                    .password(environment.getProperty("spring.second-datasource.password"))
                    .build())
            .locations("classpath:db/migration/cnaps_db")
            .load();
    flyway.baseline();
    return flyway;
  }
}