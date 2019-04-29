package com.example.demo.service;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseService {
	private static final String mybatisConfig = "mybatis-config.xml";
	private static Logger logger = LogManager.getLogger();
	public static SqlSessionFactory factory;
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(mybatisConfig));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
