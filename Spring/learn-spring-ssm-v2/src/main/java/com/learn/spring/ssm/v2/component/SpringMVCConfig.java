package com.learn.spring.ssm.v2.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.PathMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;

/**
 * SpringMVC.xml
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 08:57
 **/
@Configuration
@ComponentScan(basePackages = {"com.learn.spring.ssm.v2.controller"})
public class SpringMVCConfig extends WebMvcConfigurationSupport {

    /**
     *    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *       <property name="prefix" value="/WEB-INF/view/"></property>
     *       <property name="suffix" value=".jsp"></property>
     *       <property name="contentType" value="text/html;charset=UTF-8" />
     *    </bean>
     * @param registry
     */
    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    /**
     *     <mvc:resources mapping="/resources/**" location="/resources/" />
     *
     *     addResourceHandler("/resources/**") 表示文件路径，这里的意思是assets包下的所有文件
     *     addResourceLocations("classpath:/resources/") 表示要开放的资源
     * @param registry`
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
    }

    @Bean(name = "multipartResolver") // bean必须写name属性且必须为multipartResolver
    protected CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        commonsMultipartResolver.setMaxInMemorySize(0);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
