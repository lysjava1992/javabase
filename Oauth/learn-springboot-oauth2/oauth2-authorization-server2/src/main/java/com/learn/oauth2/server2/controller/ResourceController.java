package com.learn.oauth2.server2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ResourceController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/4/6 16:05
 * @Version 1.0
 **/
@RestController
public class ResourceController {
    /**
     * 获取当前登录用户的信息
     *
     * @param principal 当前的登录用户
     * @return 响应
     */
    @GetMapping("/resource")
    public HttpEntity<?> resource(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
