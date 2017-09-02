package com.mywjdh.logistics.logisticswechat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mywjdh.logistics.logisticswechat.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
    
    List<Order> selectByUserId(@Param("userId")Integer userId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}