package com.test.securtiy.service;

import com.test.securtiy.model.Result;

public interface AuthService {
    Result smsCode(String phone);
}
