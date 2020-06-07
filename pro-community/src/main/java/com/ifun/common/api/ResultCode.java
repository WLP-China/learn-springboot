package com.muqing.common.api;

import com.muqing.common.exception.AbstractBaseExceptionEnum;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements AbstractBaseExceptionEnum {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),//SERVICE_ERROR

    UNAUTHORIZED(501, "暂未登录或访问过期"),//TOKEN_ILLEGAL 访问过期或无效,请重新授权
    FORBIDDEN(502, "没有相关权限"),
    INVALID_OPERATION(503, "非法操作"),
    VALIDATE_FAILED(504, "参数检验失败");//PARAM_INVALID INPUT_INVALID

    /**
     * 返回代码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;

    private ResultCode(Integer code, String message) {
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
