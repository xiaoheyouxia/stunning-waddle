package com.zkn.learnspringboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * 注册拦截器
 *
 * @author SYSTEM
 * @date 2017/8/16
 */
@Component
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}