package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){

//        Profiles profiles = Profiles.of("dev", "test");
//
//        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .groupName("LinHan")    //多个分组 多人开发
//                .enable(flag) //生产测试环境开启swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.controller")) //扫描哪个包
//                .paths(PathSelectors.ant("/api/**"))  //过滤路径
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("登录",
                "实现登录和注册功能",
                "1.0",
                "urn:tos",
                new Contact("林翰","","593886981@qq.com"), //作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
