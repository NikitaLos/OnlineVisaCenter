package com.vironit.onlinevisacenter.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.vironit.onlinevisacenter.service","com.vironit.onlinevisacenter.dao.jpa"})
public class RootConfig {

}
