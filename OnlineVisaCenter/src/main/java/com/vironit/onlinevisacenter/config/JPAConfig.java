package com.vironit.onlinevisacenter.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class JPAConfig{

    private Environment environment;

    @Autowired
    public JPAConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.vironit.onlinevisacenter.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(10);
        dataSource.setDriverClassName(environment.getProperty("postgres.driver"));
        dataSource.setJdbcUrl(environment.getProperty("postgres.url"));
        dataSource.setUsername(environment.getProperty("postgres.username"));
        dataSource.setPassword(environment.getProperty("postgres.pass"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
        return properties;
    }
}
