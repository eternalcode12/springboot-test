package com.example.demo03.controller;

import com.example.demo03.comm.utils.AppResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 11:18
 * @describe 测试控制器
 * @since 1.8
 */
@RestController
@Api(value = "测试控制器", tags = "测试控制器")
public class TestController {

    /**
     * 测试接口是否通畅
     * @return AppResult
     */
    @PostMapping("/test")
    @ApiOperation("测试接口是否通畅")
    public AppResult test() {
        return AppResult.success();
    }
}
