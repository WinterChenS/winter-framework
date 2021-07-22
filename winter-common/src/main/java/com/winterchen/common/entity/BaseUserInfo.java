package com.winterchen.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础用户信息
 * @author huajiejun
 * @date 2020/7/9 11:20 上午
 */
@Data
@ApiModel("用户的基本信息")
public class BaseUserInfo implements Serializable {

    @ApiModelProperty("用户的id")
    private String id;
    @ApiModelProperty("用户类型")
    private String userType;
    @ApiModelProperty("用户的名称")
    private String name;
    @ApiModelProperty("用户的账号")
    private String account;
    @ApiModelProperty("用户的状态 0是禁用1是启用")
    private Integer state;
    @ApiModelProperty("用户的手机号")
    private String mobile;
    @ApiModelProperty("组织架构名称")
    private String frameworkName;
    @ApiModelProperty("角色id")
    private String roleId;
    @ApiModelProperty("组织架构code")
    private String frameworkCode;



}
