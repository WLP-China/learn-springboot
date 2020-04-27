package com.ifun.exception.enums;

import com.ifun.exception.AbstractBaseExceptionEnum;

/**
 * 基本异常枚举
 * Create by iFun on 2020/04/27
 */
public enum CoreExceptionEnum implements AbstractBaseExceptionEnum {
    SERVICE_ERROR(500, "服务器异常"),
    PARAM_INVALID(501, "请求参数格式非法"),
    REQUEST_METHOD_ERROR(503, "请求方式不正确"),
    CURRENT_NOT_USER(201, "无访问权限"),
    TOKEN_ILLEGAL(202, "访问过期或无效,请重新授权"),

    NO_LOGIN(1002, "当前操作需要登录，请登陆后重试"),
    INVALID_INPUT(1003, "非法输入"),
    INVALID_OPERATION(1004, "非法操作");

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String message;

    CoreExceptionEnum(Integer code, String message) {
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
