package com.test.securtiy.component.sms;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.security.core.parameters.P;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 用于暂存验证码
 */
public class SmsCodeMemory {

    private static final SmsCodeMemory PHONE_CODE_MEMORY = new SmsCodeMemory();
    private static final Map<String, SmsCode> PHONE_CODE_MAP = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
    static {
        EXECUTOR_SERVICE.schedule(SmsCodeMemory::expiredInspection, 20, TimeUnit.SECONDS);
    }
    public static SmsCodeMemory getInstance() {
        return PHONE_CODE_MEMORY;
    }

    private SmsCodeMemory() {
    }
    /**
     * 过期检查
     */
    private static void expiredInspection() {
        for (Map.Entry<String, SmsCode> item : PHONE_CODE_MAP.entrySet()) {
            if (item.getValue().isExpired()) {
                PHONE_CODE_MAP.remove(item.getKey());
            }
        }
    }




    public SmsCode getCode(String phone) {

        return PHONE_CODE_MAP.get(phone);
    }

    public SmsCode put(String phone) {

        PHONE_CODE_MAP.put(phone, new SmsCode());
        return getCode(phone);
    }

    public void remove(String phone) {

        if (PHONE_CODE_MAP.containsKey(phone)) {
            PHONE_CODE_MAP.remove(phone);
        }
    }
}
