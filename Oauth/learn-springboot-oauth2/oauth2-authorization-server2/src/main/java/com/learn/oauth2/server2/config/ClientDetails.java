package com.learn.oauth2.server2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 10:52
 **/
@Data
@Configuration
@ConfigurationProperties("application.security.oauth")
public class ClientDetails {
    private List<BaseClientDetails> client;
}
