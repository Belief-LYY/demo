package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService extends BaseService {

    public static Logger logger = LogManager.getLogger();

    public User selectByPrimaryKey(Integer id) {
        SqlSession session = factory.openSession(true);
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.selectByPrimaryKey(id);
        } finally {
            session.close();
        }
    }

    public List<User> selectAllUser() {
        SqlSession session = factory.openSession(true);
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.selectAllUser();
        } finally {
            session.close();
        }
    }

    public Integer update(User user) {
        SqlSession session = factory.openSession(true);
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.updateByPrimaryKeySelective(user);
        } finally {
            session.close();
        }
    }

    public Integer delete(Integer id) {
        SqlSession session = factory.openSession(true);
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.deleteByPrimaryKey(id);
        } finally {
            session.close();
        }
    }
}
