package com.muqing.controller;

import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.ConcreteTypeDao;
import com.muqing.model.ConcreteType;
import com.muqing.service.ConcreteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concretetype")
public class ConcreteTypeController {

    @Autowired
    private ConcreteTypeDao concreteTypeDao;
    @Autowired
    private ConcreteTypeService concreteTypeService;

    /**
     * 保存 (添加 or 修改)
     *
     * @param concreteType
     * @return
     */
    @PreAuthorize("hasAuthority('concreteType:add')")
    @PostMapping
    public void save(@RequestBody ConcreteType concreteType) {
        concreteTypeService.saveOrUpdate(concreteType);
    }

    /**
     * 修改
     *
     * @param concreteType
     * @return
     */
    @PreAuthorize("hasAuthority('concreteType:add')")
    @PutMapping
    public ConcreteType update(@RequestBody ConcreteType concreteType) {
        concreteTypeDao.update(concreteType);

        return concreteType;
    }

    /**
     * 删除
     *
     * @param id
     */
    @PreAuthorize("hasAuthority('concreteType:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        concreteTypeDao.delete(id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ConcreteType get(@PathVariable Long id) {
        return concreteTypeDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('concreteType:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return concreteTypeDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ConcreteType> list(PageTableRequest request) {
                return concreteTypeDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    /**
     * 列表 by Type
     *
     * @param type
     * @return
     */
    @GetMapping(params = "type")
    public List<ConcreteType> listByType(String type) {
        return concreteTypeDao.listByType(type);
    }
}
