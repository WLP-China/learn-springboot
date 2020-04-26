package com.ifun.exception;

/**
 * 自定义异常
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    SYS_ERROR(1001, "服务端异常，请稍后再试"),
    NO_LOGIN(1002, "当前操作需要登录，请登陆后重试"),
    INVALID_INPUT(1003, "非法输入"),
    INVALID_OPERATION(1004, "无效的操作"),

    QUESTION_NOT_FOUND(11001, "问题不存在或已被删除"),
    QUESTION_SAVE_FAIL(11002, "问题保存失败"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
