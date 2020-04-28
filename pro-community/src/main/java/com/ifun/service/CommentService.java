package com.ifun.service;

import com.ifun.mbg.mapper.CommentMapper;
import com.ifun.mbg.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;


    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
