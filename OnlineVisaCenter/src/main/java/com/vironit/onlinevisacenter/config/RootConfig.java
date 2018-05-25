package com.vironit.onlinevisacenter.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootConfiguration
@EnableAspectJAutoProxy
@ComponentScan({"com.vironit.onlinevisacenter.service","com.vironit.onlinevisacenter.dao",
        "com.vironit.onlinevisacenter.dto.converter"})
public class RootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
