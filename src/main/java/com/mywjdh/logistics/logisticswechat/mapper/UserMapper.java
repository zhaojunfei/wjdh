package com.mywjdh.logistics.logisticswechat.mapper;

import org.apache.ibatis.annotations.Param;

import com.mywjdh.logistics.logisticswechat.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User findUserByOpenid(@Param("openid")String openid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}