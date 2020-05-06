package com.ifun.service;

import com.ifun.common.exception.ServiceException;
import com.ifun.dao.CommentDao;
import com.ifun.dao.QuestionDao;
import com.ifun.enums.CommentTypeEnum;
import com.ifun.enums.exception.CommentExceptionEnum;
import com.ifun.enums.exception.QuestionExceptionEnum;
import com.ifun.mbg.mapper.CommentMapper;
import com.ifun.mbg.mapper.QuestionMapper;
import com.ifun.mbg.model.Comment;
import com.ifun.mbg.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private CommentDao commentDao;
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private QuestionDao questionDao;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new ServiceException(CommentExceptionEnum.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new ServiceException(CommentExceptionEnum.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null) {
                throw new ServiceException(CommentExceptionEnum.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {//回复过程中问题被删除的情况
                throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //追加评论数。对comment的commentCount+1
            commentDao.increaseCommentCount(comment.getParentId());

        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //追加评论数。对question的commentCount+1
            questionDao.increaseCommentCount(question.getId());
        }
    }
}
