package com.winterchen.common.actor;

import lombok.Data;

@Data
public class BaseActorMsg<T> {

    /** 子环境 */
    private String subEnv;
    /** 关联用户id */
    private String userId;
    /** 请求追踪id */
    private String traceId;

    T object;

}
