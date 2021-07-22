package com.winterchen.common.entity;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户信息
 * @author huajiejun
 * @date 2020/7/9 11:20 上午
 */
@Data
@ApiModel("用户的信息")
public class UserInfo implements Serializable {

    /** 对应存在redis的key */
    public static final String USER_INFO_REDIS = "userInfoRedis:";


    /** 管理员的code */
    public static final String ADMIN_CODE = "ADMIN";

    /** 用户id */
    @ApiModelProperty("用户的id")
    private String id;

    @ApiModelProperty("下属")
    List<String> subordinate;

    @ApiModelProperty("同科室下用户ID(包括自己)")
    List<String> sameDeptUserList;

    @ApiModelProperty("科室管理员:同科室同角色下用户ID,科教管理员、伦理管理员跨科室")
    List<String> sameDeptRoleUserList;

    /** 个人信息 */
    @ApiModelProperty("用户的基本信息")
    private BaseUserInfo baseUserInfo;

    /** 权限信息 */
    @ApiModelProperty("用户的权限信息")
    private List<UserPermResInfo> resInfos;

    /** 角色的code */
    @ApiModelProperty("角色code")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("数据审批人员code")
    private List<String> approveList;
    /**
     * 从拥有的权限中获取
     * @return
     */
    public UserPermResInfo getOwnResByCode(String resCode) {
        Map<String, UserPermResInfo> collect = Optional.ofNullable(resInfos).orElse(Lists.newArrayList()).stream().collect(Collectors.toMap(x -> x.getResCode(), v -> v, (k1, k2) -> k1));
        UserPermResInfo userPermResInfo = collect.get(resCode);
        return userPermResInfo;
    }


    /**
     * 判断是否是管理员
     * @return
     */
    public boolean thisUserIsAdmin() {
        return StringUtils.equals(ADMIN_CODE, Optional.ofNullable(roleCode).orElse(StringUtils.EMPTY));
    }



}
