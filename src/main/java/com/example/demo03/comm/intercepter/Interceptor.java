package com.example.demo03.comm.intercepter;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.comm.utils.RedisUtils;
import com.example.demo03.comm.utils.TokenUtils;
import com.example.demo03.entity.User;
import com.example.demo03.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 11:02
 * @describe 拦截器
 * @since 1.8
 */
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserMapper userMapper;

    public static ThreadLocal<Object> threadLocal = ThreadUtil.createThreadLocal(true);

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取 token
        String Authorization = httpServletRequest.getHeader("Authorization");
        JWT jwt = null;
        // 解决 options 问题
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            return true;
        }
        // 判断 token 是否为空
        if (StrUtil.isEmpty(Authorization)) {
            throw new BaseException(500, "请登录");
        }
        // 获取 token 中的用户信息
        try {
            jwt = TokenUtils.parseToken(Authorization);
        } catch (Exception e) {
            throw new BaseException(500, "登录密钥校验失败");
        }
        // 获取 redis 中的信息
        Object auth = redisUtils.get(jwt.getPayload("uid").toString());
        // 判断 redis 中是否存在该用户信息
        if (auth == null) {
            // 查库
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
            wrapper.eq(User::getUId, jwt.getPayload("uid").toString());
            User user = userMapper.selectOne(wrapper);
            // 判断用户是否存在
            if (user == null) {
                throw new BaseException(500, "用户不存在");
            } else {
                String token = TokenUtils.generateToken(user.getUsername(), user.getUId());
                // 存入 redis
                redisUtils.set(user.getUId(), token);
            }
        }
        // 设置
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", jwt.getPayload("uid"));
        map.put("username", jwt.getPayload("username"));
        threadLocal.set(map);

        return true;
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        threadLocal.remove();
    }
}
