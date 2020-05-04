package com.ifun.advice;

import com.alibaba.fastjson.JSON;
import com.ifun.common.api.CommonResult;
import com.ifun.common.api.ResultCode;
import com.ifun.common.exception.AbstractBaseExceptionEnum;
import com.ifun.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义异常处理
 * Create by iFun on 2020/04/24
 */
@ControllerAdvice()
public class CustomizeExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomizeExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handleControllerException(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        log.error("业务异常:", e);

        String contentType = request.getContentType();
        if (StringUtils.equals("application/json", contentType)) {
            //返回json
            if (e instanceof ServiceException) {
                return CommonResult.failed((AbstractBaseExceptionEnum) e);
            } else {
                return CommonResult.failed();
            }
        } else {
            // 错误页面跳转
            if (e instanceof ServiceException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", ResultCode.FAILED.getMessage());
            }
            return new ModelAndView("error");
        }
    }
/*
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            CommonResult commonResult;
            // 返回 JSON
            if (e instanceof ServiceException) {
                commonResult = CommonResult.failed((AbstractBaseExceptionEnum) e);
            } else {
                log.error("handle error", e);
                commonResult = CommonResult.failed();;
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(commonResult));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        } else {
            // 错误页面跳转
            if (e instanceof ServiceException) {
                model.addAttribute("message", e.getMessage());
            } else {
                log.error("handle error", e);
                model.addAttribute("message", ResultCode.FAILED.getMessage());
            }
            return new ModelAndView("error");
        }
    }*/

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
