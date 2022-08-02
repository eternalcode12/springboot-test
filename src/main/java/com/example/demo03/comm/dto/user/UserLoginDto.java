package com.example.demo03.comm.dto.user;

import lombok.Data;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 13:53
 * @describe 登录 dto
 * @since 1.8
 */
@Data
public class UserLoginDto {
    private String username;
    private String password;
}
