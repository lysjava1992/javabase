package com.learn.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @EnableConfigServer
 *  开启配置中心
 *   配置中心负责从本地文件或git仓库中读取配置
 *   配置中心负责其他微服务的配置拉取请求
 *
 *
 *   在仓库中配置文件的命名规范为
 *     { 应用名 } - { 环境名 }.yml
 *     { 应用名 } - { 环境名 }.properties
 *     假设微服务的Eureka-Client则配置文件名
 *     Eureka-Client.yml 相当于 Eureka-Client-default.yml 即默认配置
 *     Eureka-Client-dev.yml 即生产环境下的配置
 *
 *  请求客户端请求
 *    http://ip:端口/config-client/dev/master 可以查询分支
 *  请求具体的文件
 *   http://ip:端口/目标文件路径
 *   目标文件路径拼接规则
 *   application对应的微服务名
 *   profile环境 default还是生产环境
 *   label 在git下，git版本
 *    1.   /{application}/{profile}[/{label}]         / { 应用名 } / { 环境名 }  获取分支信息
 *    2.  /{application}-{profile}.yml                / { 应用名 } - { 环境名 }.yml
 *    3.  /{label}/{application}-{profile}.yml         / { 应用名 } - { 环境名 }.yml
 *    4.  /{application}-{profile}.properties          / { 应用名 } - { 环境名 }.properties
 *    5. /{label}/{application}-{profile}.properties   / { 分支名 } / { 应用名 } - { 环境名 }.properties
 *
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class LearnCloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnCloudConfigServerApplication.class,args);
    }
}
