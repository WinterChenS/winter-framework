package com.winterchen.common.entity;


import com.winterchen.core.bean.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huajiejun
 * @date 2020/7/10 5:20 下午
 */
@Data
public class BasePageRequest extends BasePage implements Serializable {

    @ApiModelProperty("用户id-（会用token里的userId覆盖）")
    private String userId;

}
