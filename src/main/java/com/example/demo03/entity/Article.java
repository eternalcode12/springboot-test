package com.example.demo03.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/8/2 21:48
 * @describe 文章实体类
 * @since 1.8
 */
@Data
@TableName("inis_article")
public class Article {
    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("用户唯一表示")
    @TableField(value = "u_id", fill = FieldFill.INSERT)
    private String uId;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态[1: 正常显示], [2: 草稿箱], [3: 已删除]")
    private Integer mode;
    @ApiModelProperty("访问数")
    private Long looks;
    @ApiModelProperty("点赞数")
    private Long likes;
    @ApiModelProperty("评论数")
    @TableField(exist = false)
    private Long comments;
    @ApiModelProperty("文章长度")
    private Long len;
    @ApiModelProperty("创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;
    @ApiModelProperty("更新时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;
}
