package com.muqing.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    /**
     * 父类ID
     */
    private Long parentId;
    /**
     * 父类类型[1:question , 2:comment]
     */
    private Integer type;
    /**
     * 评论内容
     */
    private String content;
}
