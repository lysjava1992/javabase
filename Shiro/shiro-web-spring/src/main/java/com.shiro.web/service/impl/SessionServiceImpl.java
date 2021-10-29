package com.shiro.web.service.impl;

import com.shiro.web.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private static final String OUT_SIGN = "OUT_SIGN";

    @Autowired
    SessionDAO sessionDAO;

    @Override
    public List<Session> find() {
        return (List<Session>) sessionDAO.getActiveSessions();
    }

    /**
     * 删除
     *  打标记
     * @param sessionId
     */
    @Override
    public void remote(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setAttribute(OUT_SIGN, true);
    }
}
