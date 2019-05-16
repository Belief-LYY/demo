package com.example.demo.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class HikariFactory extends UnpooledDataSourceFactory {

    private static final String hikariProperties = "hikari.properties";
    private static Logger logger = LogManager.getLogger();

    public HikariFactory() {
        Properties props = new Properties();
        try {
            props.load(Resources.getResourceAsStream(hikariProperties));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        this.dataSource = new HikariDataSource(new HikariConfig(props));
    }
}
