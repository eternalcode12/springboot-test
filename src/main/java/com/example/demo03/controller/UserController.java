package com.example.demo03.controller;

import cn.hutool.json.JSONObject;
import com.example.demo03.comm.dto.BaseQueryDto;
import com.example.demo03.comm.dto.user.UserRegisterDto;
import com.example.demo03.comm.exception.BaseException;
import com.example.demo03.comm.utils.AppResult;
import com.example.demo03.entity.User;
import com.example.demo03.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 13:57
 * @describe 用户控制器
 * @since 1.8
 */
@RestController
@Api(value = "用户控制器", tags = "用户控制器")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/deleteById/{id}")
    @ApiOperation("删除用户")
    public AppResult deleteById(@PathVariable("id") Long id) throws BaseException {
        userService.deleteById(id);
        return AppResult.success(200, "删除成功");
    }

    @PostMapping("/updateUser")
    @ApiOperation("更新用户")
    public AppResult update(@RequestBody User user) throws BaseException {
        userService.update(user);
        return AppResult.success(200, "更新成功");
    }

    @PostMapping("/getUserInfoById/{id}")
    @ApiOperation("根据id查询用户信息")
    public AppResult getUserInfo(@PathVariable("id") Long id) throws BaseException {
        userService.getUserInfoById(id);
        return AppResult.success(200, "查询成功");
    }

    @PostMapping("/findAllUser")
    @ApiOperation("查询所有用户")
    public AppResult findAllUsers(@RequestBody BaseQueryDto baseQueryDto) throws BaseException {
        JSONObject allUser = userService.findAllUser(baseQueryDto);
        return AppResult.success(200, "查询成功", allUser);
    }
}
