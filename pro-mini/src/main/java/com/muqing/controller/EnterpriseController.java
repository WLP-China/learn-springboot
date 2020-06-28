package com.muqing.controller;

import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.EnterpriseDao;
import com.muqing.model.Enterprise;
import com.muqing.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业相关接口
 * Create by iFun on 2020/06/28
 */
@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private EnterpriseDao enterpriseDao;

    /**
     * 保存企业
     *
     * @param enterprise
     */
    @PostMapping
    @PreAuthorize("hasAuthority('enterprise:add')")
    public void save(@RequestBody Enterprise enterprise) {
        enterpriseService.saveOrUpdate(enterprise);
    }

    /**
     * 删除企业
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('enterprise:del')")
    public void delete(@PathVariable Long id) {
        enterpriseService.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('enterprise:query')")
    public Enterprise get(@PathVariable Long id) {
        return enterpriseDao.getById(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('enterprise:query')")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(
                new CountHandler() {
                    @Override
                    public int count(PageTableRequest request) {
                        return enterpriseDao.count(request.getParams());
                    }
                },
                new ListHandler() {
                    @Override
                    public List<Enterprise> list(PageTableRequest request) {
                        List<Enterprise> list = enterpriseDao.list(request.getParams(), request.getOffset(), request.getLimit());
                        return list;
                    }
                }
        ).handle(request);
    }
}