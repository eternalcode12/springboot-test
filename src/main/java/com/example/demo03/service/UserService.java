package com.example.demo03.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo03.comm.dto.BaseQueryDto;
import com.example.demo03.comm.dto.user.UserLoginDto;
import com.example.demo03.comm.dto.user.UserRegisterDto;
import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.entity.User;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 13:58
 * @describe 用户服务接口
 * @since 1.8
 */
public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param userRegisterDto 注册用户信息
     * @throws BaseException  注册异常
     */
    void register(UserRegisterDto userRegisterDto) throws BaseException;

    /**
     * 用户登录
     * @param userLoginDto 登录用户信息
     * @return 用户信息
     * @throws BaseException 登录异常
     */
    JSONObject login(UserLoginDto userLoginDto) throws BaseException;

    /**
     * 根据id删除用户
     * @param id 用户id
     * @throws BaseException 删除异常
     */
    void deleteById(Long id) throws BaseException;

    /**
     * 更新用户信息
     * @param user 用户实体信息
     * @throws BaseException 更新异常
     */
    void update(User user) throws BaseException;

    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return 用户信息
     * @throws BaseException 查询异常
     */
    User getUserInfoById(Long id) throws BaseException;

    /**
     * 查询所有用户
     * @param baseQueryDto 基本查询条件
     * @return 用户列表
     * @throws BaseException 查询异常
     */
    JSONObject findAllUser(BaseQueryDto baseQueryDto) throws BaseException;
}
