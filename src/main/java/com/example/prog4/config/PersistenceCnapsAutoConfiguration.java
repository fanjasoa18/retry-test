package com.example.prog4.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.example.prog4.repository.cnaps",
        entityManagerFactoryRef = "cnapsEntityManager",
        transactionManagerRef = "cnapsTransactionManager")
public class PersistenceCnapsAutoConfiguration {
  @Primary
  @Bean(name = "cnapsDataSource")
  @ConfigurationProperties(prefix="spring.second-datasource")
  public DataSource cnapsDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "cnapsEntityManager")
  public LocalContainerEntityManagerFactoryBean cnapsEntityManager(
          @Qualifier("cnapsDataSource") final DataSource dataSource
  ) {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");


    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManagerFactoryBean.setDataSource(dataSource);
    localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.prog4.repository.cnaps.entity");
    localContainerEntityManagerFactoryBean.setJpaProperties(properties);
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    return  localContainerEntityManagerFactoryBean;
  }

  @Bean(name = "cnapsTransactionManager")
  public PlatformTransactionManager cnapsTransactionManager(
          @Qualifier("cnapsEntityManager") final EntityManagerFactory cnapsEntityManager
  ) {
    return new JpaTransactionManager(cnapsEntityManager);
  }
}