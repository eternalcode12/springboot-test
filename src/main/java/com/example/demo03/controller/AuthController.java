package com.example.demo03.controller;

import cn.hutool.json.JSONObject;
import com.example.demo03.comm.dto.user.UserLoginDto;
import com.example.demo03.comm.dto.user.UserRegisterDto;
import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.comm.utils.AppResult;
import com.example.demo03.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 13:59
 * @describe 用户公开接口
 * @since 1.8
 */
@RestController
@RequestMapping("/auth")
@Api(value = "用户公开接口", tags = "用户公开接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public AppResult register(@RequestBody UserRegisterDto userRegisterDto) throws BaseException {
        // userService
        userService.register(userRegisterDto);
        return AppResult.success(200, "注册成功");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public AppResult login(@RequestBody UserLoginDto userLoginDto) throws BaseException {
        // userService
        JSONObject jsonObject = userService.login(userLoginDto);
        return AppResult.success(200, "登录成功", jsonObject);
    }
}
