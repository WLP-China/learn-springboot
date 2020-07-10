package com.muqing.service;

import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderSellerDTO;
import com.muqing.model.Order;

/**
 * Create by iFun on 2020/07/01
 */
public interface OrderService {

    int save(OrderDTO orderDTO);

    int update(Order order);

    int update(OrderSellerDTO orderSellerDTO);

    /**
     * 添加卖方
     *
     * @param id 订单ID
     * @param eid 卖方ID
     * @return
     */
    int addSeller(Long id, Long eid);
}
