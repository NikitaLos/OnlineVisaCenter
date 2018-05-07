package com.vironit.onlinevisacenter.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
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

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.NON_CONTEXTUAL_LOB_CREATION;
import static org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS;


@SpringBootConfiguration
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
        properties.setProperty(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty(DIALECT, environment.getProperty("hibernate.dialect"));
        properties.setProperty(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.setProperty(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
        properties.setProperty(USE_SQL_COMMENTS, environment.getProperty("hibernate.use_sql_comments"));
        properties.setProperty(NON_CONTEXTUAL_LOB_CREATION, environment.getProperty("hibernate.jdbc.lob.non_contextual_creation"));
        return properties;
    }
}
