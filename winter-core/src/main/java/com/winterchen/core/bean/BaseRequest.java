package com.winterchen.core.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author winterchen
 * @date 2020/7/10 5:20 下午
 */
@Data
public class BaseRequest implements Serializable {

    private String userId;

}
