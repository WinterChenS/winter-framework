package com.winterchen.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/** 用户的权限信息
 *
 * @author huajiejun
 * @date 2020/7/12 1:39 下午
 */
@Data
@ApiModel("用户权限相关信息")
public class UserPermResInfo implements Serializable {

    /** 资源的id */
    @ApiModelProperty("资源id")
    private String id;
    /** 特殊属性的值 */
    @ApiModelProperty("一些其他字段的值，用map存储")
    private String otherData;
    /** 特殊的属性 */
    @ApiModelProperty("其他字段的配置信息")
    private String specialConfig;
    /** 资源的名称 */
    @ApiModelProperty("资源的名称")
    private String resName;
    /** 资源的code */
    @ApiModelProperty("资源的code")
    private String resCode;
    @ApiModelProperty("父类标签")
    private String parentCode;
    @ApiModelProperty("资源类型 1为永久 2为临时")
    private Integer resType;

    @ApiModelProperty("资源开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("资源结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("是否存在这个资源")
    private boolean hasThisRes;
}
