package com.example.demo.dao;

import com.example.demo.model.ChannelHttp;

public interface ChannelHttpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChannelHttp record);

    int insertSelective(ChannelHttp record);

    ChannelHttp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelHttp record);

    int updateByPrimaryKey(ChannelHttp record);
}