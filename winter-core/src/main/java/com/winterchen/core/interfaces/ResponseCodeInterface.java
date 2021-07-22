package com.winterchen.core.interfaces;

/**
 * @function 响应码规范接口
 * @author winterchen
 * @date 2021/7/22
 */
public interface ResponseCodeInterface {
	
	 /*响应吗*/
	 long getCode();
	 
	 /*响应消息*/
	 String getMessage();
	 
}
