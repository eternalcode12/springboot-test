package com.example.demo03.comm.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 11:10
 * @describe 结果类
 * @since 1.8
 */
@Data
public class AppResult<T> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(AppResult.class);

    @ApiModelProperty(value = "状态码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> AppResult<T> success() {
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setCode(200);
        serverResponseEntity.setMsg("成功");
        return serverResponseEntity;
    }

    public static <T> AppResult<T> success(Integer code, String msg) {
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setCode(code);
        serverResponseEntity.setMsg(msg);
        return serverResponseEntity;
    }

    public static <T> AppResult<T> success(Integer code, String msg, T data) {
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setData(data);
        serverResponseEntity.setCode(code);
        serverResponseEntity.setMsg(msg);
        return serverResponseEntity;
    }


    public static <T> AppResult<T> failure(int code, String msg) {
        log.error(msg.toString());
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setMsg(msg);
        serverResponseEntity.setCode(code);
        return serverResponseEntity;
    }

    public static <T> AppResult<T> failure(int code, String msg, T data) {
        log.error(msg.toString());
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setMsg(msg);
        serverResponseEntity.setCode(code);
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    public static <T> AppResult<T> failure(String msg, Integer code) {
        AppResult<T> serverResponseEntity = new AppResult<>();
        serverResponseEntity.setMsg(msg);
        serverResponseEntity.setCode(code);
        return serverResponseEntity;
    }
}
