package com.course.business.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author EaApple
 * @6/7/2020 9:59 AM
 * description：
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)//这个注解抛出异常  //抛出的异常被自定义的异常类接收到
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e){
        ResponseDto responseDto =  new ResponseDto();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());//调试使用
        responseDto.setMessage("请求参数异常");// 模糊返回警告
        return responseDto;
    }
}
