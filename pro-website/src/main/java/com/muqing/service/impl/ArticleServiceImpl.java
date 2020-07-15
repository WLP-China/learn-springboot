package com.muqing.service.impl;

import com.muqing.dao.ArticleDao;
import com.muqing.model.Article;
import com.muqing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by iFun on 2020/07/15
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public int saveOrUpdate(Article article) {
        if (article.getId() != null) {
            return articleDao.update(article);
        } else {
            return articleDao.save(article);
        }
    }
}
