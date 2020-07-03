package com.muqing.service.impl;

import com.muqing.dao.OrderDao;
import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderSellerDTO;
import com.muqing.model.Order;
import com.muqing.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by iFun on 2020/07/01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int save(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setStatus(Order.Status.DAISHENHE);
        return orderDao.save(order);
    }

    @Override
    public int update(OrderDTO orderDTO) {
        return 0;
    }

    @Override
    public int update(OrderSellerDTO orderSellerDTO) {
        return 0;
    }
}
