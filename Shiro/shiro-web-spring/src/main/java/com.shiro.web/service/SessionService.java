package com.shiro.web.service;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.List;

public interface SessionService {
    public List<Session> find();
    public void  remote(String sessionId);
}
