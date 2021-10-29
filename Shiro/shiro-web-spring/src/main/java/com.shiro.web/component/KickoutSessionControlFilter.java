package com.shiro.web.component;

import com.google.gson.Gson;
import com.shiro.web.model.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class KickoutSessionControlFilter extends AccessControlFilter {
    private Cache<String, Deque> kickoutCache;
    private SessionManager sessionManager;
    private static final String OUT_SIGN="OUT_SIGN";
    private  static  final int MAX=1;
    private static  final  boolean PRE=false;
    public KickoutSessionControlFilter(CacheManager cacheManager, SessionManager sessionManager) {
        kickoutCache = cacheManager.getCache("kickoutCache");
        this.sessionManager = sessionManager;
    }

    /**
     * 判断是否登录
     * 在登录的情况下会走此方法，此方法返回true直接访问控制器
     * isAccessAllowed方法返回True，则不会再调用onAccessDenied方法
     * 返回false,再调用onAccessDenied方法
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * 是否是拒绝登录
     * 没有登录的情况下会走此方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() || subject.isRemembered()) {
            //未登录
            return true;
        }
        String username = (String) subject.getPrincipal();
        //一个用户维护一个session队列
        Deque<Serializable> sessionDeque = kickoutCache.get(username);
        if (sessionDeque == null) {
            sessionDeque = new ArrayDeque<>();
            kickoutCache.put(username, sessionDeque);
        }
        Session session=subject.getSession();
        Serializable sessionId= session.getId();
        // 未超出
       if(!sessionDeque.contains(sessionId)&&session.getAttribute(OUT_SIGN)==null){
           sessionDeque.push(sessionId);
       }
       //超出需要删除
        while (sessionDeque.size()>MAX){
            if(PRE){
                 sessionId=   sessionDeque.removeFirst();
            }else {
                  sessionId= sessionDeque.removeLast();
            }
            // 打标志
              Session outSession=  sessionManager.getSession(new DefaultSessionKey(sessionId));
              outSession.setAttribute(OUT_SIGN,true);
        }
        // 下次操作时提出
        if (session.getAttribute(OUT_SIGN) != null) {
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            if(isAjaxRequest((HttpServletRequest) request)){
                Gson gson=new Gson();
                response.getWriter().write(gson.toJson(new Result(false,"Your account is logged in elsewhere!!")));
            }else {
                saveRequest(request);
                WebUtils.issueRedirect(request, response, "/login");
            }
            return false;
        }
        return true;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
