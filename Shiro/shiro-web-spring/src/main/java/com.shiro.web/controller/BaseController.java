package com.shiro.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.shiro.web.model.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestControllerAdvice
public class BaseController {
    /**
     * 权限异常
     *
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if(isAjaxRequest(request)){
            Result result=new Result(false,"当前用户无权限");
            writeJson(result, response);

        }
        return null;
    }

    private void writeJson(Result result, HttpServletResponse response) {
        Gson gson=new Gson();
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(gson.toJson(result));
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * 是否是Ajax请求
     * @Title: isAjaxRequest
     * @Description: TODO
     * @Date 2018年4月11日 下午2:19:31
     * @author OnlyMate
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
