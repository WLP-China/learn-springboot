package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.dto.CommentCreateDTO;
import com.muqing.enums.exception.CommentExceptionEnum;
import com.muqing.mbg.model.Comment;
import com.muqing.mbg.model.User;
import com.muqing.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论 Controller
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public CommonResult post(@RequestBody CommentCreateDTO commentCreateDTO,
                             HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            return CommonResult.unauthorized(null);
        }
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return CommonResult.failed(CommentExceptionEnum.CONTENT_IS_EMPTY);
        }

        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setContent(commentCreateDTO.getContent());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setCommentCount(0);

        commentService.insert(comment);

        return CommonResult.success(null);
    }
}
