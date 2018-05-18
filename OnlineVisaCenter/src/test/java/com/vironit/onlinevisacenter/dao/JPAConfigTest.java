package com.vironit.onlinevisacenter.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.NON_CONTEXTUAL_LOB_CREATION;
import static org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS;

@SpringBootConfiguration
@PropertySource("classpath:database.properties")
public class JPAConfigTest {
    private Environment environment;

    @Autowired
    public JPAConfigTest(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("test")
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(10);
        dataSource.setDriverClassName(environment.getProperty("test.postgres.driver"));
        dataSource.setJdbcUrl(environment.getProperty("test.postgres.url"));
        dataSource.setUsername(environment.getProperty("test.postgres.username"));
        dataSource.setPassword(environment.getProperty("test.postgres.pass"));
        return dataSource;
    }

    @Bean
    @Profile("test")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean testEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        testEntityManagerFactory.setDataSource(dataSource());
        testEntityManagerFactory.setPackagesToScan("com.vironit.onlinevisacenter.entity");
        testEntityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        testEntityManagerFactory.setJpaProperties(hibernateProperties());
        return testEntityManagerFactory;
    }

    @Profile("test")
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, environment.getProperty("test.hibernate.hbm2ddl.auto"));
        properties.setProperty(DIALECT, environment.getProperty("test.hibernate.dialect"));
        properties.setProperty(SHOW_SQL, environment.getProperty("test.hibernate.show_sql"));
        properties.setProperty(FORMAT_SQL, environment.getProperty("test.hibernate.format_sql"));
        properties.setProperty(USE_SQL_COMMENTS, environment.getProperty("test.hibernate.use_sql_comments"));
        properties.setProperty(NON_CONTEXTUAL_LOB_CREATION, environment.getProperty("test.hibernate.jdbc.lob.non_contextual_creation"));
        return properties;
    }
}
