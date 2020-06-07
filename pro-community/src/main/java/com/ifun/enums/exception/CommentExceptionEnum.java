package com.muqing.enums.exception;

import com.muqing.common.exception.AbstractBaseExceptionEnum;

public enum  CommentExceptionEnum implements AbstractBaseExceptionEnum {
    CONTENT_IS_EMPTY(10201, "提交内容为空"),
    TARGET_PARAM_NOT_FOUND(10202, "未选中任何问题或评论进行回复"),
    TYPE_PARAM_WRONG(10203, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(10204,"回复的评论不存在")
    ;

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String message;

    CommentExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
