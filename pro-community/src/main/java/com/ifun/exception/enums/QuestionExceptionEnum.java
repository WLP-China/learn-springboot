package com.ifun.exception.enums;

import com.ifun.exception.AbstractBaseExceptionEnum;

/**
 * Create by iFun on 2020/04/27
 */
public enum QuestionExceptionEnum implements AbstractBaseExceptionEnum {
    QUESTION_NOT_FOUND(11001, "问题不存在或已被删除"),
    QUESTION_SAVE_FAIL(11002, "问题保存失败");

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String message;

    QuestionExceptionEnum(Integer code, String message) {
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
