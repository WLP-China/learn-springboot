package com.ifun.enums.exception;

import com.ifun.common.exception.AbstractBaseExceptionEnum;

public enum  CommentExceptionEnum implements AbstractBaseExceptionEnum {
    CONTENT_IS_EMPTY(10201, "提交内容为空");

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
