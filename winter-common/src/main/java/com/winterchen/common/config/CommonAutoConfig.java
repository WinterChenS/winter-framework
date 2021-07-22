package com.winterchen.common.config;

import akka.actor.ActorSystem;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.winterchen.common.actor.ActorRefBean;
import com.winterchen.common.aop.GlobalExceptionAspect;
import com.winterchen.common.aop.LogicExceptionAspect;
import com.winterchen.common.filter.CustomInterceptor;
import com.winterchen.common.mybatisplus.BaseEntityMetaObjectHandler;
import com.winterchen.common.swagger.SwaggerDisplayConfig;
import com.winterchen.common.utils.UserInfoUtils;

import com.winterchen.common.web.BaseController;
import com.winterchen.common.web.FeignAddHeaderRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;

import static com.winterchen.common.actor.SpringExtension.SpringExtProvider;

/**
 * @author winterchen
 * @date 2021/7/4 1:13 下午
 */
@Configuration
@ConditionalOnProperty(havingValue = "true",name="common.autoconfig")
public class CommonAutoConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystem");
        SpringExtProvider.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

    @Bean
    public ActorRefBean actorRefBean(ActorSystem actorSystem){
        return new ActorRefBean(actorSystem);
    }


    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean(value = PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    @Bean
    @ConditionalOnMissingBean(value = ISqlInjector.class)
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    @ConditionalOnMissingBean(value = OracleKeyGenerator.class)
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }


    @Bean
    @ConditionalOnMissingBean(value = MetaObjectHandler.class)
    public BaseEntityMetaObjectHandler baseEntityMetaObjectHandler() {
        return new BaseEntityMetaObjectHandler();
    }


    @Bean
    public LogicExceptionAspect logicExceptionAspect() {
        return new LogicExceptionAspect();
    }


    /**
     * 拦截器配置 用户登录信息的自动拦截
     * @return
     */
    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }



    @Bean
    public UserInfoUtils userInfoUtils(){
        return new UserInfoUtils();
    }

    @Bean
    @ConditionalOnMissingBean(value = RequestInterceptor.class)
    public FeignAddHeaderRequestInterceptor feignAddHeaderRequestInterceptor() {
        return new FeignAddHeaderRequestInterceptor();
    }


    @Bean
    @ConditionalOnMissingBean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean(value = ResponseEntityExceptionHandler.class)
    public BaseController baseController() {
        return new BaseController();
    }


    @Bean
    @ConditionalOnMissingBean(value = ModelPropertyBuilderPlugin.class)
    public SwaggerDisplayConfig swaggerDisplayConfig() {
        return new SwaggerDisplayConfig();
    }


    @Bean
    public GlobalExceptionAspect globalExceptionAspect() {
        return new GlobalExceptionAspect();
    }
}
