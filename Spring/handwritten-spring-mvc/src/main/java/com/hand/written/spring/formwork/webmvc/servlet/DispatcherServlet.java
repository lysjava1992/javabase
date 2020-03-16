package com.hand.written.spring.formwork.webmvc.servlet;

import com.hand.written.spring.formwork.annotation.Controller;
import com.hand.written.spring.formwork.annotation.RequestMapping;
import com.hand.written.spring.formwork.annotation.RequestParam;
import com.hand.written.spring.formwork.aop.ProxyUtil;
import com.hand.written.spring.formwork.context.ApplicationContext;
import com.hand.written.spring.formwork.webmvc.HandlerAdapter;
import com.hand.written.spring.formwork.webmvc.HandlerMapping;
import com.hand.written.spring.formwork.webmvc.ModelAndView;
import com.hand.written.spring.formwork.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.font.TextHitInfo;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DispatcherServlet
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:28
 * @Version 1.0
 **/
public class DispatcherServlet extends HttpServlet {
    private final String LOCATION="configureLocation";

    /**
     *SpringMVC 核心
     */
    private List<HandlerMapping> handlerMappings=new ArrayList<>();

    private Map<HandlerMapping,HandlerAdapter> handlerAdapters=new HashMap<>();

    private List<ViewResolver> viewResolvers=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
        doDispatcher(req,resp);
    }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().write("500 Exception,详情："+e.getStackTrace());
    }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HandlerMapping mappedHandler = getHandler(req);
            if(mappedHandler==null){
                resp.getWriter().write("404 Not Found...");
            }
            HandlerAdapter ha = getHandlerAdapter(mappedHandler);
            ModelAndView mv = ha.handle(req, resp, mappedHandler);

            processDispatchResult(resp, mv);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws IOException {
        //调用resolveViewName
        if(null==mv){return;}
        if(this.viewResolvers.isEmpty()){return;}
        for (ViewResolver viewResolver:this.viewResolvers){
            if(!mv.getViewName().equals(viewResolver.getViewName())){
               continue;
            }
            String out=viewResolver.viewResolver(mv);
            if(out!=null){
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping mappedHandler) {
        if(this.handlerAdapters.isEmpty()){return null;}
        return this.handlerAdapters.get(mappedHandler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {

        if(this.handlerMappings.isEmpty()){return null;}
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url=url.replace(contextPath,"").replaceAll("/+","/");
        for (HandlerMapping handler:this.handlerMappings){
            Matcher matcher=handler.getPattern().matcher(url);
            if(!matcher.matches()){continue;}
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        ApplicationContext context=new ApplicationContext(config.getInitParameter(LOCATION));
        try {
            initStrategies(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 九大组件
     * @param context
     */
    private void initStrategies(ApplicationContext context) throws Exception {

        //通过HandlerMapping将请求映射到处理器
        initHandlerMappings(context);

        //进行多类型的参数动态匹配
        initHandlerAdapters(context);


        //解析逻辑视图到具体视图实现
        initViewResolvers(context);






        //主题解析
        initThemeResolver(context);
        //文件上传解析
        initMultipartResolver(context);
        // 本地化是添加地区特定的组件和翻译文本，使得国际化软件适合特定地区或语言
        initLocaleResolver(context);
        //执行过程中遇到异常，由HandlerExceptionResolvers来负责解析
        initHandlerExceptionResolvers(context);
        //直接解析请求到视图名
        initRequestToViewNameTranslator(context);
        //flash映射管理器
        initFlashMapManager(context);
    }
    /**
     * 用来保存url 和 controller中Method的对应关系
     * @param context
     */
    private void initHandlerMappings(ApplicationContext context) throws Exception {
            String[] beanNames=context.getBeanDefinitionNames();
            for (String beanName:beanNames){
                Object proxy=context.getBean(beanName);
                Object instance= ProxyUtil.getTargetObject(proxy);
                Class<?> clazz=instance.getClass();
                if(clazz.isAnnotationPresent(Controller.class)){
                    String baseUrl="";
                    if(clazz.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
                        baseUrl=requestMapping.value().trim();
                    }
                    Method[] methods=clazz.getMethods();
                    for (Method method:methods){
                        if(method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                            String regex=("/"+baseUrl+requestMapping.value().trim()).replaceAll("/+","/");
                            Pattern pattern=Pattern.compile(regex);
                            this.handlerMappings.add(new HandlerMapping(pattern,instance,method));
                            System.out.println("Mapping: "+regex+", "+method);
                        }
                    }
                }
            }
    }
    /**
     * 动态匹配请求参数与Method,和动态转换赋值
     * @param context
     */
    private void initHandlerAdapters(ApplicationContext context) {
        //将参数的名称或类型顺序保存下来  反射调用，传的形参是一个数组
        //可以通过记录这些参数的位置index,挨个从数组中填充0
        for (HandlerMapping handlerMapping:this.handlerMappings){
            Map<String,Integer>paramMapping=new HashMap<>();
            Annotation[][] pa=handlerMapping.getMethod().getParameterAnnotations();
            for (int i=0;i<pa.length;i++){
                for (Annotation a:pa[i]){
                      if(a instanceof RequestParam){
                          String  paramName=((RequestParam) a).value();
                          if(!"".equals(paramName.trim())){
                              paramMapping.put(paramName,i);
                          }
                      }
                }
            }
            //非命名参数
            Class<?>[] paramTypes=handlerMapping.getMethod().getParameterTypes();
            for (int i=0;i<paramTypes.length;i++){
                Class<?> type=paramTypes[i];
                if(type == HttpServletRequest.class|| type==HttpServletResponse.class){
                    paramMapping.put(type.getName(),i);
                }
            }

            this.handlerAdapters.put(handlerMapping,new HandlerAdapter(paramMapping));

        }


    }

    /**
     * 动态模板解析
     * @param context
     */
    private void initViewResolvers(ApplicationContext context) {
       String templateRoot=context.getConfig().getProperty("template.root");
        templateRoot=this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir=new File(templateRoot);
        for (File tempalte:templateRootDir.listFiles()){
            this.viewResolvers.add(new ViewResolver(tempalte.getName(),tempalte));
        }

    }


    private void initFlashMapManager(ApplicationContext context) {

    }
    private void initRequestToViewNameTranslator(ApplicationContext context) {

    }
    private void initHandlerExceptionResolvers(ApplicationContext context) {

    }
    private void initThemeResolver(ApplicationContext context) {

    }
    private void initLocaleResolver(ApplicationContext context) {

    }
    private void initMultipartResolver(ApplicationContext context) {

    }



}
