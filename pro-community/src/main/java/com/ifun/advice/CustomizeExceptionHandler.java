package com.ifun.advice;

import com.ifun.exception.ServiceException;
import com.ifun.exception.enums.CoreExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理
 * Create by iFun on 2020/04/24
 */
@ControllerAdvice()
public class CustomizeExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomizeExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model) {
        log.error("业务异常:",e);
        // 错误页面跳转
        if (e instanceof ServiceException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", CoreExceptionEnum.SERVICE_ERROR.getMessage());
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
