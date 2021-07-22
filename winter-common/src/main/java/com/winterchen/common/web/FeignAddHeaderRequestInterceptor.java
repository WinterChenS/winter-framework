package com.winterchen.common.web;


import com.winterchen.common.utils.ConstantsUtil;
import com.winterchen.common.utils.UserInfoUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class FeignAddHeaderRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String uid = UserInfoUtils.getUserIdByCurrent();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        template.header(ConstantsUtil.USER_ID, uid);
        template.header(ConstantsUtil.USER_TYPE, UserInfoUtils.getUserTypeByCurrent());
        template.header(ConstantsUtil.IP_ADDRESS, request.getHeader(ConstantsUtil.IP_ADDRESS));
    }

}