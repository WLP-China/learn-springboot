package com.ifun.dao;

public interface QuestionDao {

    /**
     * 阅读数+1
     * @param questionId
     * @return
     */
    int increaseViewCount(Integer questionId);

    /**
     * 回复数+1
     * @param questionId
     * @return
     */
    int increaseCommentCount(Integer questionId);
}
