package com.winterchen.nacos.rest;

import com.winterchen.common.web.BaseController;
import com.winterchen.core.bean.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/7/22 11:07
 **/
@Api("nacos api")
@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosController extends BaseController {

    @Value("${test.config.refresh:true}")
    private boolean refresh;

    @GetMapping("/")
    public CommonResult<Boolean> get() {
        return CommonResult.success(refresh);
    }

}