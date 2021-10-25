package com.learn.brixton.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤
 */
@Component
public class AccessFilter extends ZuulFilter {

    /**
     *
     * @return 过滤器类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     *
     * @return 多个过滤器 时，执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *
     * @return 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx=RequestContext.getCurrentContext();
        //当前请求数据
        HttpServletRequest request=ctx.getRequest();
        System.out.println(request.getMethod()+"----"+request.getRequestURI().toString());
        Object token=request.getParameter("accessToken");
        if(token==null){
            System.out.println("access token is null");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        System.out.println("access token ok :"+token);
        return null;
    }
}
