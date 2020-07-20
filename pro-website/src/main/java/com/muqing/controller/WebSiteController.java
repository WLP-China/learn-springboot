package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.dao.ArticleDao;
import com.muqing.dto.PaginationDTO;
import com.muqing.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/16
 */
@RestController
@RequestMapping("/web")
public class WebSiteController {

    @Autowired
    private ArticleDao articleDao;

    @GetMapping("/article")
    public CommonResult<PaginationDTO<Article>> list(@RequestParam(name = "type",required = false) String type,
                                                     @RequestParam(name = "title", required = false) String title,
                                                     @RequestParam(name = "pagenum", defaultValue = "1") Integer pagenum,
                                                     @RequestParam(name = "pagesize", defaultValue = "5") Integer pagesize) {
        //SELECT .. FROM `t_article` WHERE status=1 ORDER BY isTop DESC,publishTime DESC,createTime DESC
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("title", title);
        params.put("status", "1");
        params.put("orderBy", " ORDER BY isTop DESC,publishTime DESC,createTime DESC ");

        List<Article> list = articleDao.list(params, pagesize * (pagenum - 1), pagesize);
        if (list == null) {
            return CommonResult.failed();
        }
        int totalCount = articleDao.count(params);
        Integer totalPage;
        if (totalCount % pagesize == 0) {
            totalPage = totalCount / pagesize;
        } else {
            totalPage = totalCount / pagesize + 1;
        }
      
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setData(list);
        paginationDTO.setTotalCount(totalCount);
        paginationDTO.setTotalPage(totalPage);
        paginationDTO.setCurrentPage(pagenum);
        return CommonResult.success(paginationDTO);
    }

    @GetMapping("/article/{id}")
    public Article get(@PathVariable Long id) {
        return articleDao.getById(id);
    }
}
