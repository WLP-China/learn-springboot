package com.ifun.dao;

public interface CommentDao {

    /**
     * 回复数+1
     *
     * @param commentId
     * @return
     */
    int increaseCommentCount(Long commentId);
}
