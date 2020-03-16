package com.hand.written.spring.formwork.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HandlerAdapter
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 16:32
 * @Version 1.0
 **/
public class HandlerAdapter {
    private Map<String,Integer> paramMapping;
    public HandlerAdapter(Map<String,Integer> paramMapping) {
        this.paramMapping=paramMapping;
    }

    /**
     *
     * @param req 参数解析
     * @param resp  只是为了将其赋值给方法参数
     * @param mappedHandler
     *           controller method url
     * @return
     */

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping mappedHandler) throws InvocationTargetException, IllegalAccessException {
       Class<?>[] paramTypes=mappedHandler.getMethod().getParameterTypes();

       Map<String,String[]> reqParameterMap= req.getParameterMap();

       Object[] paramValues=new Object[paramTypes.length];
       for (Map.Entry<String,String[]> param:reqParameterMap.entrySet()){

           //String value= Arrays.toString(param.getValue()).replaceAll("\\[|\\]",",");
           String value=param.getValue()[0];
           if(!this.paramMapping.containsKey(param.getKey())){continue;}
             int index=this.paramMapping.get(param.getKey());
             paramValues[index]=caseStringValue(value,paramTypes[index]);
       }
       if(this.paramMapping.containsKey(HttpServletRequest.class.getName())){
           int reqIndex=this.paramMapping.get(HttpServletRequest.class.getName());
           paramValues[reqIndex]=req;
       }
        if(this.paramMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex=this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex]=resp;
        }


       Object result=mappedHandler.getMethod().invoke(mappedHandler.getController(),paramValues);
       if(result==null){
           return null;
       }
       boolean isModelAndView=mappedHandler.getMethod().getReturnType()==ModelAndView.class;
       if(isModelAndView){
           return (ModelAndView) result;
       }
        return null;
    }

    private Object caseStringValue(String value,Class<?>calzz){
        if(calzz==String.class){
            return value;
        }else if(calzz== Integer.class){
            return Integer.valueOf(value);
        }else if(calzz==int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }
    }
}
