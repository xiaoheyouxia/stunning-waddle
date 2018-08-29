package com.zkn.learnspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger配置类
 * @author: lxh
 * @create: 2018-03-30 09:57
 **/
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.zkn.learnspringboot.web.controller" })
public class SwaggerConfig {
    /**
     *
     * Description: swagger标题描述相关
     *
     * @author luoluocaihong
     * @taskId <br>
     * @return <br>
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LearningSpringBoot项目 APIs")
                .description("这里是描述")
                .license("license许可地址")
                .licenseUrl("")
                .termsOfServiceUrl("www.g.cn")
                .version("1.0.0")
                .build();
    }

    /**
     *
     * Description: <br>
     *
     * @author luoluocaihong<br>
     * @taskId <br>
     * @return <br>
     */
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zkn.learnspringboot.web.controller"))
                .build()
                .apiInfo(apiInfo());
    }

}
