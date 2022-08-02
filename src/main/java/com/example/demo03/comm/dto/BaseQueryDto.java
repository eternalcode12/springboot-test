package com.example.demo03.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author eternalcoder
 * @version 1.0
 * @date 2022/7/26 11:38
 * @describe 基础查询dto
 * @since 1.8
 */
@Data
@ApiModel
public class BaseQueryDto implements Serializable {

    @ApiModelProperty(value = "当前页码")
    private Integer currentNo;

    @ApiModelProperty(value = "每页显示条数")
    private Integer pageSize;

    @ApiModelProperty(value = "全部查询")
    private Boolean all;

    //分页参数合理化校验
    public void checkPageParam() {
        if (this.currentNo == null || this.currentNo <= 0) {
            setCurrentNo(1);
        }
        if (this.pageSize == null || this.pageSize <= 0 || this.pageSize > 1000) {
            setPageSize(10);
        }
    }
}