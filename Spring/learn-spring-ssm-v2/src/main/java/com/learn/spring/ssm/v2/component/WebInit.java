package com.learn.spring.ssm.v2.component;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Web.xml
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 08:58
 **/
public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        initializeSpringConfig(servletContext);
        

        initializeSpringMVCConfig(servletContext);

         initializeDruidWebConfig(servletContext);

//        registerServlet(servletContext);
//
//        registerListener(servletContext);
//
         registerFilter(servletContext);


    }

    private void registerFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("DruidWebStatFilter", WebStatFilter.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    }

    private void initializeDruidWebConfig(ServletContext servletContext) {
        StatViewServlet statViewServlet=new StatViewServlet();
        ServletRegistration.Dynamic dynamic=servletContext.addServlet("DruidStatView", statViewServlet);
        dynamic.addMapping("/druid/*");

    }


    /** <servlet>
     *    <servlet-name>Spring-Dispatcher</servlet-name>
     *    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     *     <init-param>
     *       <param-name>contextConfigLocation</param-name>
     *       <param-value>classpath:spring/spring-mvc.xml</param-value>
     *    </init-param>
     * </servlet>
     * <servlet-mapping>
     *    <servlet-name>Spring-Dispatcher</servlet-name>
     *    <url-pattern>/</url-pattern>
     * </servlet-mapping>
     *
     * @param servletContext
     */
    private void initializeSpringMVCConfig(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(SpringMVCConfig.class);
        ServletRegistration.Dynamic dispacther=servletContext.addServlet("Spring-Dispatcher",
                                                new DispatcherServlet(mvcContext));
        dispacther.setLoadOnStartup(1);
        dispacther.setAsyncSupported(true);
        dispacther.addMapping("/");
    }

    /**
     *   <listener>
     *       <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     *   </listener>
     *   <context-param>
     *      <param-name>contextConfigLocation</param-name>
     *      <param-value>classpath:spring/spring.xml</param-value>
     *   </context-param>
     * @param servletContext
     */
    private void initializeSpringConfig(ServletContext servletContext){
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

    }
}
