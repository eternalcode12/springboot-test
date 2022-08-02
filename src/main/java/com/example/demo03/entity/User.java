package com.example.demo03.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 11:40
 * @describe 用户实体类
 * @since 1.8
 */
@Data
@TableName("inis_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "u_id", fill = FieldFill.INSERT)
    private String uId;
    private String nickname;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private String description;
    @TableField(value = "is_admin", fill = FieldFill.INSERT)
    private Integer isAdmin;
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
