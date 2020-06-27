package com.muqing.controller;

import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.DictDao;
import com.muqing.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dicts")
public class DictController {

    @Autowired
    private DictDao dictDao;

    /**
     * 添加
     *
     * @param dict
     * @return
     */
    @PreAuthorize("hasAuthority('dict:add')")
    @PostMapping
    public Dict save(@RequestBody Dict dict) {
        Dict d = dictDao.getByTypeAndK(dict.getType(), dict.getK());
        if (d != null) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        dictDao.save(dict);

        return dict;
    }

    /**
     * 修改
     *
     * @param dict
     * @return
     */
    @PreAuthorize("hasAuthority('dict:add')")
    @PutMapping
    public Dict update(@RequestBody Dict dict) {
        dictDao.update(dict);

        return dict;
    }

    /**
     * 删除
     *
     * @param id
     */
    @PreAuthorize("hasAuthority('dict:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dictDao.delete(id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Dict get(@PathVariable Long id) {
        return dictDao.getById(id);
    }

    /**
     * 列表 for datatables
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('dict:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return dictDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Dict> list(PageTableRequest request) {
                return dictDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
    public List<Dict> listByType(String type) {
        return dictDao.listByType(type);
    }
}
