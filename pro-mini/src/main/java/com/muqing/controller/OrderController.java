package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.common.utils.UserUtil;
import com.muqing.dao.OrderDao;
import com.muqing.dto.LoginUserDTO;
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

    /**
     * 新增订单
     *
     * @param orderDTO
     */
    @PostMapping
    @PreAuthorize("hasAuthority('order:add')")
    public void save(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }

    @PostMapping("/addSeller")
    @PreAuthorize("hasAuthority('order:add')")
    public CommonResult addSeller(@RequestParam(name = "id") Long id,
                                  @RequestParam(name = "eid") Long eid) {
        int i = orderService.addSeller(id, eid);
        if (i != 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
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
