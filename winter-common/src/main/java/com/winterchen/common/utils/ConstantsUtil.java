/*
 * Copyright (C) 2018 royal Inc., All Rights Reserved.
 */
package com.winterchen.common.utils;

/**
 * @Description: 静态变量工具类
 * @Author: yeyuelong
 * @Date: 创建时间 2017年7月17日 下午4:05:15
 */
public class ConstantsUtil {

	/**
	 * 登陆密码加密秘钥
	 */
	public static final String SECRET_KEY = "winterchen";

	/**
	 * 动态api的token
	 */
	public static final String SECRET_KEY_DP_GATEWAY = "GATEWAY_KEY";

	/**
	 * redis的key
	 */
	public static final String REDIS_GATEWAY_MAP = "redisGatewayMap";

	/**
	 * 初始密码
	 */
	public static final String DEFAULT = "123456";




	/**
	 * 数据库更新或插入失败条数
	 */
	public static final int SQL_FAILURE = 0;

	/**
	 * token中携带的信息
	 */
	public static final String USERID = "userId";

	/**
	 * HEARD中携带的token的字段
	 */
	public static final String TOKEN = "token";

	/**
	 * HEARD中携带的userId的字段
	 */
	public static final String USER_ID = "USER_ID";

	public static final String USER_TYPE = "USER_TYPE";

	public static final String IP_ADDRESS = "ip_address";

	public static final String USER_NAME = "USER_NAME";

	public static final String SESSION_KEY = "sessionKey";

	/**
	 * doctors用户token主题
	 */
	public static final String USER = "doctors_user";





}
