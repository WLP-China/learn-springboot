package com.ifun.dto;

import com.ifun.mbg.model.User;
import lombok.Data;

/**
 * Create by iFun on 2020/05/08
 */
@Data
public class CommentDTO {

    private Long id;
    private Long parentId;//父类ID
    private Integer type;//父类类型[1:question , 2:comment]
    private String content;//评论内容
    private Long commentator;//评论人ID
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;//点赞数
    private Integer commentCount;//回复数
    private User user;
}
