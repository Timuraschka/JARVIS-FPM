package edu.fra.uas.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "edu.fra.uas.user.repository", "edu.fra.uas.resource.repository", "edu.fra.uas.project.repository", "edu.fra.uas.Task.repository", "edu.fra.uas.Timetracker.repository" })
public class JPAConfig {

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(new String[] { "edu.fra.uas.resource.repository", "edu.fra.uas.project.repository", "edu.fra.uas.task.repository", "edu.fra.uas.timetracker.repository" });
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());
    return em;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(applicationContext.getEnvironment().getProperty("jdbc.driverClassName"));
    dataSource.setUrl(applicationContext.getEnvironment().getProperty("jdbc.url"));
    dataSource.setUsername(applicationContext.getEnvironment().getProperty("jdbc.user"));
    dataSource.setPassword(applicationContext.getEnvironment().getProperty("jdbc.pass"));
    return dataSource;
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", applicationContext.getEnvironment().getProperty("hibernate.hbm2ddl.auto"));
    properties.setProperty("hibernate.dialect", applicationContext.getEnvironment().getProperty("hibernate.dialect"));
    return properties;
  }

}