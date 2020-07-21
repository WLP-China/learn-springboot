package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.DepartmentDao;
import com.muqing.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科室
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 添加/修改
     *
     * @param department
     * @return
     */
    @PreAuthorize("hasAuthority('department:add')")
    @PostMapping
    public CommonResult save(@RequestBody Department department) {
        if (department.getIsTop() == null) {
            department.setIsTop("0");
        }

        int resule = 0;
        if (department.getId() != null) {
            resule = departmentDao.update(department);
        } else {
            resule = departmentDao.save(department);
        }

        if (resule == 1) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("操作失败");
    }


    /**
     * 删除
     *
     * @param id
     */
    @PreAuthorize("hasAuthority('department:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentDao.delete(id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Department get(@PathVariable Long id) {
        return departmentDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('department:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return departmentDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Department> list(PageTableRequest request) {
                return departmentDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

}
