package com.muqing.dao;

import com.muqing.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Create by iFun on 2020/07/15
 */
@Mapper
public interface ArticleDao {

    @Select("select * from t_article t where t.id = #{id}")
    Article getById(Long id);

    @Delete("delete from t_article where id = #{id}")
    int delete(Long id);

    int update(Article article);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_article(type, title, content, publishTime, isTop, status, createTime, updateTime) values(#{type}, #{title}, #{content}, #{publishTime}, #{isTop}, #{status}, now(), now())")
    int save(Article article);

    int count(@Param("params") Map<String, Object> params);

    List<Article> list(@Param("params") Map<String, Object> params,
                       @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    int increaseReadCount(Long articleId);
}
