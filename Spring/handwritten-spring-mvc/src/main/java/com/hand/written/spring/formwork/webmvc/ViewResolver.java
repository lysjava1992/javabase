package com.hand.written.spring.formwork.webmvc;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *     将静态文件变为动态文件
 *     最终输出String
 * @ClassName ViewResolver
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 16:33
 * @Version 1.0
 **/
public class ViewResolver {
    private String viewName;
    private File template;

    public ViewResolver(String viewName, File template) {
        this.viewName = viewName;
        this.template = template;
    }

    public String viewResolver(ModelAndView mv) throws IOException {
        StringBuilder sb=new StringBuilder();
        RandomAccessFile ra=new RandomAccessFile(this.template,"r");
        String line=null;
        while (null!=(line=ra.readLine())){
              Matcher m=matcher(line);
              while (m.find()){
                  for (int i=0;i<m.groupCount();i++){
                      String paramName=m.group(i);
                      paramName=paramName.substring(2,paramName.length()-1);
                      Object paramValue=mv.getModel().get(paramName);
                      if(null==paramValue){continue;}
                      line=line.replaceAll("\\$\\{"+paramName+"\\}",paramValue.toString());
                  }
              }
              sb.append(line);
        }
        return sb.toString();
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
  private Matcher matcher(String str) {
      Pattern pattern=Pattern.compile("\\$\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
      Matcher matcher=pattern.matcher(str);
      return matcher;
  }

}
