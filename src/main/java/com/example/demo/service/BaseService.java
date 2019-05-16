package com.example.demo.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BaseService {
    private static final String mybatisConfig = "mybatis-config.xml";
    public static SqlSessionFactory factory;
    private static Logger logger = LogManager.getLogger();

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(mybatisConfig));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
