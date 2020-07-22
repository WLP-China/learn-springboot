package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.DepartmentDao;
import com.muqing.dao.DoctorDao;
import com.muqing.model.Department;
import com.muqing.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专家医生
 */
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 添加/修改
     *
     * @param doctor
     * @return
     */
    @PreAuthorize("hasAuthority('doctor:add')")
    @PostMapping
    public CommonResult save(@RequestBody Doctor doctor) {
        Department department = departmentDao.getById(doctor.getDepartmentId());
        if (department == null) {
            return CommonResult.failed("未选择科室");
        }
        doctor.setDepartmentName(department.getName());

        int resule = 0;
        if (doctor.getId() != null) {
            resule = doctorDao.update(doctor);
        } else {
            resule = doctorDao.save(doctor);
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
    @PreAuthorize("hasAuthority('doctor:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorDao.delete(id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Doctor get(@PathVariable Long id) {
        return doctorDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('doctor:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return doctorDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<Doctor> list(PageTableRequest request) {
                return doctorDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}
