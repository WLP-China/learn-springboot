package com.ifun.controller;

import com.ifun.common.api.CommonResult;
import com.ifun.dto.CommentCreateDTO;
import com.ifun.enums.exception.CommentExceptionEnum;
import com.ifun.mbg.model.Comment;
import com.ifun.mbg.model.User;
import com.ifun.service.CommentService;
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
    CommentService commentService;

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
        //TODO id类型不对应 重新生成model.User后修改此处
        comment.setCommentator(Long.valueOf(user.getId()));
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0);
        comment.setCommentCount(0);

        commentService.insert(comment);

        return CommonResult.success(null);
    }
}
