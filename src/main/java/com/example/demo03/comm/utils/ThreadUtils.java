package com.example.demo03.comm.utils;

import com.example.demo03.comm.intercepter.Interceptor;

import java.util.HashMap;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/2 17:16
 * @describe 线程工具类
 * @since 1.8
 */
public class ThreadUtils {

    /**
     * 获取当前线程
     * @return 当前线程
     */
    public static HashMap<String, Object> getLock() {
        Object o = Interceptor.threadLocal.get();
        return (HashMap<String, Object>) o;
    }
}
