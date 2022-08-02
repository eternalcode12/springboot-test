package com.example.demo03.comm.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/20 14:39
 * @describe 基础异常类
 * @since 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends Exception {

    private int code;
    private String msg;

    public BaseException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
