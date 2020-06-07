package com.muqing.dao;

public interface QuestionDao {

    /**
     * 阅读数+1
     * @param questionId
     * @return
     */
    int increaseViewCount(Long questionId);

    /**
     * 回复数+1
     * @param questionId
     * @return
     */
    int increaseCommentCount(Long questionId);
}
