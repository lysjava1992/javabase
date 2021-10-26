package com.learn.chapter14.quota;

/**
 * 请求配额判断
 */
public interface QuotaService {

    boolean isQuotaExceeded(String key);
}
