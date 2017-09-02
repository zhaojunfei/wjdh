package com.mywjdh.logistics.logisticswechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywjdh.logistics.logisticswechat.domain.User;
import com.mywjdh.logistics.logisticswechat.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public User findUserByOpenid(String openid){
		return userMapper.findUserByOpenid(openid);
	}
	public int insert(User record){
		return userMapper.insert(record);
	}
}
