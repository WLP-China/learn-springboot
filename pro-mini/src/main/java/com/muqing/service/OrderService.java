package com.muqing.service;

import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderSellerDTO;

/**
 * Create by iFun on 2020/07/01
 */
public interface OrderService {

    /**
     * 新增订单
     *
     * @param orderDTO
     * @return
     */
    int save(OrderDTO orderDTO);

    /**
     * 更新订单
     *
     * @param orderDTO
     * @return
     */
    int update(OrderDTO orderDTO);

    /**
     * 更新订单-添加发货单
     *
     * @param orderSellerDTO
     * @return
     */
    int update(OrderSellerDTO orderSellerDTO);
}
