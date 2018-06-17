package com.vironit.onlinevisacenter.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class JPAConfig{

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.vironit.onlinevisacenter.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(hibernateProperties);
        return entityManagerFactory;
    }

    @Bean
    public DataSource dataSource(@Value("${postgres.url}") String url, @Value("${postgres.driver}") String driver,
                                 @Value("${postgres.username}") String username, @Value("${postgres.pass}") String pass){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(10);
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public Properties hibernateProperties(@Value("${hibernate.schema_prod}") String schema,@Value("${hibernate.dialect}") String dialect,
                                           @Value("${hibernate.show_sql}") String showSql, @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
                                           @Value("${hibernate.format_sql}") String formatSQL, @Value("${hibernate.use_sql_comments}") String sqlComments,
                                           @Value("${hibernate.jdbc.lob.non_contextual_creation}") String lob) {
        Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, hbm2ddl);
        properties.setProperty(DIALECT, dialect);
        properties.setProperty(SHOW_SQL, showSql);
        properties.setProperty(FORMAT_SQL, formatSQL);
        properties.setProperty(USE_SQL_COMMENTS, sqlComments);
        properties.setProperty(NON_CONTEXTUAL_LOB_CREATION, lob);
        properties.setProperty(DEFAULT_SCHEMA, schema);
        return properties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
