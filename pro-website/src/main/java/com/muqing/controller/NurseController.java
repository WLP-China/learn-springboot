package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.NurseDao;
import com.muqing.model.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专家医生
 */
@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseDao nurseDao;

    /**
     * 添加/修改
     *
     * @param nurse
     * @return
     */
    @PreAuthorize("hasAuthority('nurse:add')")
    @PostMapping
    public CommonResult save(@RequestBody Nurse nurse) {
        int resule = 0;
        if (nurse.getId() != null) {
            resule = nurseDao.update(nurse);
        } else {
            resule = nurseDao.save(nurse);
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
    @PreAuthorize("hasAuthority('nurse:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        nurseDao.delete(id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Nurse get(@PathVariable Long id) {
        return nurseDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('nurse:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return nurseDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<Nurse> list(PageTableRequest request) {
                return nurseDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }
}
