package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.common.utils.UserUtil;
import com.muqing.dao.EnterpriseDao;
import com.muqing.dao.OrderDao;
import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.OrderDTO;
import com.muqing.dto.OrderVO;
import com.muqing.dto.PaginationDTO;
import com.muqing.model.Enterprise;
import com.muqing.model.Order;
import com.muqing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private EnterpriseDao enterpriseDao;

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

    /**
     * 审核订单，指定供方
     *
     * @param id  订单ID
     * @param eid 供方ID
     * @return
     */
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

    /**
     * 分页获取当前登陆用户的订单列表
     * 需方-获取自己下的订单；供方-获取分配给自己的订单；贸易公司-获取所有
     *
     * @param pagenum
     * @param pagesize
     * @return
     */
    @GetMapping("/owns")
//    @PreAuthorize("hasAuthority('order:query')")
    public CommonResult<PaginationDTO<OrderVO>> list(@RequestParam(name = "pagenum") Integer pagenum,
                                                     @RequestParam(name = "pagesize") Integer pagesize) {
        Map<String, Object> params = new HashMap<>();

        LoginUserDTO loginUserDTO = UserUtil.getLoginUser();
        Long loginUserEid = loginUserDTO.getEid();
        Enterprise loginUserE = enterpriseDao.getById(loginUserEid);
        if (loginUserE.getType() == 1) {
            //需方
            params.put("buyerEnterpriseId", loginUserEid);
        } else if (loginUserE.getType() == 2) {
            //供方
            params.put("sellerEnterpriseId", loginUserEid);
        }
        params.put("orderBy", " ORDER BY createTime DESC ");

        List<OrderVO> list = orderDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        Integer totalCount = orderDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);

        return CommonResult.success(paginationDTO);
    }
}