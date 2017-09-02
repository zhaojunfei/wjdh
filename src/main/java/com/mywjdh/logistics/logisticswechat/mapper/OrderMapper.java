package com.mywjdh.logistics.logisticswechat.mapper;

import com.mywjdh.logistics.logisticswechat.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}