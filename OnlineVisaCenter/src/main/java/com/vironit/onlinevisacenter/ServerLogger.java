package com.vironit.onlinevisacenter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServerLogger {

    private Logger logger;

    public ServerLogger(Class classType) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger = LogManager.getLogger(classType);
    }

    public void info(String message){
        logger.info(message);
    }

    public void error(String message,Exception e){
        logger.error(message,e);
    }

    public void warn(String message){
        logger.warn(message);
    }

    public void fatal(String message){
        logger.fatal(message);
    }

}
