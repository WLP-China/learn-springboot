package com.muqing.controller;

import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.OrderDao;
import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderVO;
import com.muqing.model.Order;
import com.muqing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单
 * Create by iFun on 2020/07/02
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;

    @PostMapping
    @PreAuthorize("hasAuthority('order:add')")
    public void save(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('order:query')")
    public Order get(@PathVariable Long id) {
        return orderDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasAuthority('order:query')")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(
                new CountHandler() {
                    @Override
                    public int count(PageTableRequest request) {
                        return orderDao.count(request.getParams());
                    }
                },
                new ListHandler() {
                    @Override
                    public List<OrderVO> list(PageTableRequest request) {
                        List<OrderVO> list = orderDao.list(request.getParams(), request.getOffset(), request.getLimit());
                        return list;
                    }
                }
        ).handle(request);
    }

}
