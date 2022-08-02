package com.example.demo03.comm.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/20 20:22
 * @describe JWT tools
 * @since 1.8
 */
public class TokenUtils {

    private static final String secret = "eternalcoder";

    /**
     * 生辰 token
     * @author eternalcoder
     * @param username 用户名
     * @param uid 用户唯一标识
     * @return token
     */
    public static String generateToken(String username, String uid) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("username", username);
                put("uid", uid);
            }
        };

        return JWTUtil.createToken(map, secret.getBytes());
    }

    /**
     * 解析 token
     * @param token token
     * @return token信息
     */
    public static JWT parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt;
    }

    /**
     * 验证 token
     * @param token token
     * @return 验证结果
     */
    public static Boolean verifyToken(String token) {
        return JWTUtil.verify(token, secret.getBytes());
    }
}
