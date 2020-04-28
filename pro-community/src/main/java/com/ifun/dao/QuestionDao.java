package com.ifun.dao;

import com.ifun.mbg.model.Question;

public interface QuestionDao {

    /**
     * 阅读数+1
     * @param questionId
     * @return
     */
    int increaseView(Integer questionId);
}
