package com.learn.spring.boot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-25 17:08
 **/
@ConfigurationProperties(prefix = "custom.hello")
public class HelloProperties {

private String prefix="默认前缀";
private String suffix="默认后缀";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
