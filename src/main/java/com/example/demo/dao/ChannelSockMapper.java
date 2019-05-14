package com.example.demo.dao;

import com.example.demo.model.ChannelSock;

public interface ChannelSockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChannelSock record);

    int insertSelective(ChannelSock record);

    ChannelSock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelSock record);

    int updateByPrimaryKey(ChannelSock record);
}