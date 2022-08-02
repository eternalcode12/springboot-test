package com.example.demo03.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户唯一id")
    @TableField(value = "u_id", fill = FieldFill.INSERT)
    private String uId;
    @ApiModelProperty("用户名")
    private String nickname;
    @ApiModelProperty("登录名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("描述")
    private String description;
    @TableField(value = "is_admin", fill = FieldFill.INSERT)
    @ApiModelProperty("权限")
    private Integer isAdmin;
    @ApiModelProperty("状态")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createdAt;
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date updatedAt;
}
