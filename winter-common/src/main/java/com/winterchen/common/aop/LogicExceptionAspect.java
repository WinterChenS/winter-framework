package com.winterchen.common.aop;

import com.alibaba.fastjson.JSONObject;

import com.winterchen.common.utils.MyFastJsonUtil;
import com.winterchen.common.utils.StackTraceUtil;
import com.winterchen.common.utils.UserInfoUtils;
import com.winterchen.core.bean.CommonResult;
import com.winterchen.core.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 拦截logic层的SystemException
 */
@Slf4j
@Component
@Aspect
public class LogicExceptionAspect implements Ordered {

    /**
     * 配置环绕通知,使用在方法aspect()上注册的切入点
     */
    @Around("execution(* com.winterchen.*.rest..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userIdByCurrent = UserInfoUtils.getUserIdByCurrent();
        //打印请求参数，如果需要打印其他的信息可以到request中去拿
        log.info("[{}] 请求的地址:[{}],请求参数:{}",userIdByCurrent ,request.getRequestURI(), Arrays.toString(joinPoint.getArgs()));
        try {
            Object proceed = joinPoint.proceed(joinPoint.getArgs());
            //打印返回值信息
            log.info("[{}] 响应结果:[{}]", userIdByCurrent, MyFastJsonUtil.toJSONString(proceed));
            return proceed;


        } catch (BusinessException ex) {

            String msg = "";

            if (StringUtils.isNotEmpty(ex.getMsg())) {
                msg = ex.getMsg();
            }

            log.warn("业务异常 {}\t exception : {}",
                    parseMethodInfo(joinPoint), msg);

            return CommonResult.businessFailed(ex.getCode(), ex.getMsg(), ex.getOtherInfo());

        } catch (Throwable throwable) {

            log.error("2.0 xt 系统错误 {}\t exception : {}",
                    parseMethodInfo(joinPoint), StackTraceUtil.getStackTrace(throwable));

            throw throwable;
        }
    }


    private String parseMethodInfo(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] params = methodSignature.getParameterNames();
        StringBuilder builder = new StringBuilder(joinPoint.getSignature().toString() + "参数: ");
        try {
            for (int i = 0; i < args.length; i++) {
                builder.append(",").append(params[i]).append(":").append(JSONObject.toJSONString(args[i]));
            }
        } catch (Throwable ex) {
            //ignore
        }

        return builder.toString();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
