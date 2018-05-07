package com.vironit.onlinevisacenter.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootConfiguration
@ComponentScan({"com.vironit.onlinevisacenter.service","com.vironit.onlinevisacenter.dao.jpa"})
public class RootConfig {

}
