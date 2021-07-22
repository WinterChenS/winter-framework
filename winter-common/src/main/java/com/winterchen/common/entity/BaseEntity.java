package com.winterchen.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    /**
     * 删除标志位，已删除
     */
    public static final Integer DEL_FLAG_TRUE = 1;
    /**
     * 删除标志位，未删除
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    @TableField(value="create_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableLogic
    @TableField(value="del_flag",fill = FieldFill.INSERT)
    private Integer delFlag;



    public void initBean() {
        setDelFlag(DEL_FLAG_FALSE);
        setCreateTime(new Date());
        setUpdateTime(new Date());
    }
}
