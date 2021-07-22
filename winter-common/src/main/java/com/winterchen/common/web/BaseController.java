package com.winterchen.common.web;


import com.winterchen.core.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author huajiejun
 * @Date 2020/3/12 10:36 上午
 */
@ControllerAdvice
@Slf4j
public class BaseController extends ResponseEntityExceptionHandler {



    @Autowired
    protected HttpServletRequest request;


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleRuntimeException(RuntimeException ex){
        log.debug("handleRuntimeException", ex);
        return CommonResult.failed(ex.getMessage());
    }

    @Override
    public ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getError(ex.getBindingResult().getAllErrors()) , status);
    }


    /**
     * 解决 JSON 请求统一返回参数
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getError(ex.getBindingResult().getAllErrors()) , HttpStatus.OK);
    }

    private CommonResult<Object> getError(List<ObjectError> allErrors) {
        StringBuffer message = new StringBuffer();
        for(ObjectError error: allErrors){
            message.append(error.getDefaultMessage()).append(" & ");
        }
        // 因为&两边空格
        log.info(message.substring(0, message.length() - 3));
        String substring = message.substring(0, message.length() - 3);
        return CommonResult.businessFailed(substring);
    }

}
