package com.learn.springboot.chapter10.domain;

import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 14:47
 **/
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException( String message) {
        super(message);
    }
}
