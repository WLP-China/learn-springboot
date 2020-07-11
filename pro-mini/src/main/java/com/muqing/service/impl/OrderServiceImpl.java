package com.muqing.service.impl;

import com.alibaba.fastjson.JSON;
import com.muqing.dao.EnterpriseDao;
import com.muqing.dao.OrderDao;
import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderSellerDTO;
import com.muqing.model.Enterprise;
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

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public int save(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setStatus(Order.Status.DAISHENHE);
        return orderDao.save(order);
    }

    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int update(OrderSellerDTO orderSellerDTO) {
        return 0;
    }

    @Override
    public int addSeller(Long id, Long eid) {
        Order orderById = orderDao.getById(id);
        Integer orderStatus = orderById.getStatus();
        if (orderStatus == 1 || orderStatus == 2) {
            Enterprise e = enterpriseDao.getById(eid);
            if (e != null) {
                Order order = new Order();
                order.setId(orderById.getId());
                order.setSellerEnterpriseId(e.getId());
                order.setSellerEnterprise(e.geteName());
                order.setStatus(Order.Status.DAIFAHUO);
                return orderDao.update(order);
            }
        }
        return 0;
    }

    @Override
    public int addSentInfo(OrderSellerDTO orderSellerDTO) {
        Order orderById = orderDao.getById(Long.valueOf(orderSellerDTO.getOrderId()));
        Integer orderStatus = orderById.getStatus();
        if (orderStatus == 2) {
            Order order = new Order();
            order.setId(orderById.getId());
            order.setNumCend(orderSellerDTO.getNumCend());
            order.setpJianzhan(JSON.toJSONString(orderSellerDTO.getpJianzhan()));
            order.setpDriver(JSON.toJSONString(orderSellerDTO.getpDriver()));
            order.setStatus(Order.Status.YIFAHUO);
            return orderDao.update(order);
        }
        return 0;
    }
}
