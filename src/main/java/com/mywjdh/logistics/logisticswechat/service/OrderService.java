package com.mywjdh.logistics.logisticswechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywjdh.logistics.logisticswechat.domain.Order;
import com.mywjdh.logistics.logisticswechat.mapper.OrderMapper;

@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public int insert(Order record) {
		return orderMapper.insert(record);
	}
	
	public List<Order> selectByUserId(Integer userId){
		return orderMapper.selectByUserId(userId);
	}
	
	public void updateByPrimaryKeySelective(Order record){
		 orderMapper.updateByPrimaryKeySelective(record);
	}
}
