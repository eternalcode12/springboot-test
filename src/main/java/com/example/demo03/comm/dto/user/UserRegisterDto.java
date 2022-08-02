package com.example.demo03.comm.dto.user;

import lombok.Data;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/1 13:50
 * @describe 注册dto
 * @since 1.8
 */
@Data
public class UserRegisterDto extends UserLoginDto {
    private String nickname;
}
