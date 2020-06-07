package com.muqing.service;

import com.muqing.common.exception.ServiceException;
import com.muqing.dao.CommentDao;
import com.muqing.dao.QuestionDao;
import com.muqing.dto.CommentDTO;
import com.muqing.enums.CommentTypeEnum;
import com.muqing.enums.exception.CommentExceptionEnum;
import com.muqing.enums.exception.QuestionExceptionEnum;
import com.muqing.mbg.mapper.CommentMapper;
import com.muqing.mbg.mapper.QuestionMapper;
import com.muqing.mbg.mapper.UserMapper;
import com.muqing.mbg.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired(required = false)
    private UserMapper userMapper;

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
            if (dbComment == null) {
                throw new ServiceException(CommentExceptionEnum.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {//回复过程中问题被删除的情况
                throw new ServiceException(QuestionExceptionEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //追加评论数。对comment的commentCount+1
            commentDao.increaseCommentCount(comment.getParentId());

        } else {
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

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        example.setOrderByClause("gmt_create desc");//按创建时间 逆序
        List<Comment> comments = commentMapper.selectByExample(example);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        // 获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);

        // 获取评论人并转换为 Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOList;
    }
}
