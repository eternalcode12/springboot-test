package com.example.demo03.comm.exception;

import com.example.demo03.comm.utils.AppResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/20 14:35
 * @describe 全局异常处理类
 * @since 1.8
 */
@RestController
@Service
@ControllerAdvice
public class GlobalExceptionHanler {
    private final Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public AppResult<?> baseErrorHandler(HttpServletRequest req, BaseException e) {
        logger.error("Request URL : {}, Request Method: {}, Exception : {}", req.getRequestURL(), req.getMethod(), e.getMsg());
        return AppResult.failure(e.getCode(), e.getMsg());
    }
}
