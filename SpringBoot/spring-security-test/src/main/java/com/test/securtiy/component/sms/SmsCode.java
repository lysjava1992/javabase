package com.test.securtiy.component.sms;

import java.util.Random;

public class SmsCode {
    private String code;
    private long birthday;
    private long liveTime;

    public SmsCode() {
        this.code = createRandomCode();
        this.birthday = System.currentTimeMillis();
        this.liveTime = 1000 * 60;
    }

    private static String createRandomCode() {
        StringBuilder code = new StringBuilder();
        Random random=new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public String getCode() {

        return code;
    }

    public long getBirthday() {
        return birthday;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - this.birthday > liveTime;
    }
}
