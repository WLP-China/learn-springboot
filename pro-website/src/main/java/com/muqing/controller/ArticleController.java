package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.ArticleDao;
import com.muqing.model.Article;
import com.muqing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by iFun on 2020/07/15
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('article:add')")
    public CommonResult saveOrUpdate(@RequestBody Article article) {
        int resule = articleService.saveOrUpdate(article);
        if (resule == 1) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("操作失败");
    }

    @PreAuthorize("hasAuthority('article:del')")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int resule = articleDao.delete(id);
        if (resule == 1) {
            return CommonResult.success(null);
        }
        return CommonResult.failed("操作失败");
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleDao.getById(id);
    }

    @PreAuthorize("hasAuthority('article:query')")
    @GetMapping(params = {"start", "length"})
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return articleDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<Article> list(PageTableRequest request) {
                return articleDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

}
