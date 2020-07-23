package com.muqing.service.impl;

import com.alibaba.fastjson.JSON;
import com.muqing.dao.EnterpriseDao;
import com.muqing.dao.OrderDao;
import com.muqing.dao.OrderSellerDao;
import com.muqing.dto.OrderConfirmBuyerDTO;
import com.muqing.dto.OrderConfirmSellerDTO;
import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderSellerDTO;
import com.muqing.model.Enterprise;
import com.muqing.model.Order;
import com.muqing.model.OrderSeller;
import com.muqing.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by iFun on 2020/07/01
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderSellerDao orderSellerDao;

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
    @Transactional
    public int addSentInfo(OrderSellerDTO orderSellerDTO) {
        Order orderById = orderDao.getById(Long.valueOf(orderSellerDTO.getOrderId()));
        Integer orderStatus = orderById.getStatus();
        if (orderStatus == 2 || orderStatus == 3) {
            if (orderStatus == 2) {
                Order order = new Order();
                order.setId(orderById.getId());
                order.setStatus(Order.Status.YIFAHUO);
                orderDao.update(order);
            }

            Integer countByOrderId = orderSellerDao.countByOrderId(orderById.getId());
            OrderSeller orderSeller = new OrderSeller();
            orderSeller.setOrderId(orderById.getId());
            orderSeller.setNo(countByOrderId + 1);
            orderSeller.setNumSend(orderSellerDTO.getNumSend());
            orderSeller.setpJianzhan(JSON.toJSONString(orderSellerDTO.getpJianzhan()));
//            orderSeller.setpDriver(JSON.toJSONString(orderSellerDTO.getpDriver()));
            return orderSellerDao.save(orderSeller);
        }
        return 0;
    }

    @Override
    public int buyerConfirm(OrderConfirmBuyerDTO confirmBuyerDTO) {
        Order orderById = orderDao.getById(Long.valueOf(confirmBuyerDTO.getOrderId()));
        Integer orderStatus = orderById.getStatus();
        if (orderStatus == 3) {
            Order order = new Order();
            order.setId(orderById.getId());
            order.setNumReceive(confirmBuyerDTO.getNumReceive());
            order.setpReceive(JSON.toJSONString(confirmBuyerDTO.getpReceive()));
            order.setpPangzhanA(JSON.toJSONString(confirmBuyerDTO.getpPangzhanA()));
            order.setStatus(Order.Status.YIQIANSHOU);
            return orderDao.update(order);
        }
        return 0;
    }

    @Override
    public int sellerConfirm(OrderConfirmSellerDTO confirmSellerDTO) {
        Order orderById = orderDao.getById(Long.valueOf(confirmSellerDTO.getOrderId()));
        Integer orderStatus = orderById.getStatus();
        if (orderStatus == 4) {
            Order order = new Order();
            order.setId(orderById.getId());
            order.setpPangzhanB(JSON.toJSONString(confirmSellerDTO.getpPangzhanB()));
            order.setStatus(Order.Status.YIWANCHENG);
            return orderDao.update(order);
        }
        return 0;
    }

    @Override
    public Order getById(Long id) {
        Order order = orderDao.getById(id);
        List<OrderSeller> orderSellerList = orderSellerDao.listByOrderId(id);
        if (orderSellerList != null) {
            Double totle = Double.valueOf(0);
            for (OrderSeller orderSeller : orderSellerList) {
                totle += orderSeller.getNumSend();
            }
            order.setOrderSellerList(orderSellerList);
            order.setTotleSend(totle);
        }
        return order;
    }

    @Override
    public int delete(Long id) {
        Order orderById = orderDao.getById(id);
        if (orderById.getStatus() == 1) {
            return orderDao.delete(id);
        }
        return 0;
    }
}
