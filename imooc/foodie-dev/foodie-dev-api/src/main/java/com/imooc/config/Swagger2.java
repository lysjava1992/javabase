package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Swagger2
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 16:46
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {
    //http://127.0.0.1:8088/swagger-ui.html
    //http://127.0.0.1:8088/doc.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电商平台接口api")
                .description("后端API文档")
                .termsOfServiceUrl("http://127.0.0.1:8088")
                .contact(new Contact("Tom","",""))
                .version("1.0")
                .build();
    }
}
