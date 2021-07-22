package com.winterchen.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.winterchen.common.utils.ConstantsUtil;
import com.winterchen.common.utils.CustomHttpServletRequestWrapper;
import com.winterchen.core.bean.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        pushUserInfo2Body(request, handlerMethod);

        return true;
    }

    private void pushUserInfo2Body(HttpServletRequest request, HandlerMethod handlerMethod) {
        try {

            String userId = request.getHeader(ConstantsUtil.USER_ID);

            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            if (ArrayUtils.isEmpty(methodParameters)) {
                return;
            }
            for (MethodParameter methodParameter : methodParameters) {
                Class clazz = methodParameter.getParameterType();
                if (org.springframework.util.ClassUtils.isAssignable(BaseRequest.class, clazz) ) {
                    if (request instanceof CustomHttpServletRequestWrapper) {
                        CustomHttpServletRequestWrapper requestWrapper = (CustomHttpServletRequestWrapper) request;
                        String body = requestWrapper.getBody();
                        JSONObject param = null;
                        try {
                            param = JSONObject.parseObject(body);
                            if (StringUtils.isNotBlank(userId) && param != null) {
                                param.put("userId", userId);
                            }
                            requestWrapper.setBody(JSON.toJSONString(param));

                        } catch (Exception e) {

                        }

                    }
                } else {

                }
            }
        } catch (Exception e) {

            log.warn("fill userInfo to request body Error ", e);
        }
    }

    private void pushUserInfo2ThreadLocal(){
        // TODO
    }
}