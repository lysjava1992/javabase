package com.handbook.zookeeper.base;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *  定义zookeeper 服务器地址及仓库地址
 * @ClassName ZKConfig
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:27
 * @Version 1.0
 **/
public class ZKConfig {
    public  String serverIps;
    public String repositoryName;
    public int timeOut;

    public ZKConfig(String serverIps, String repositoryName) {
        this.serverIps = serverIps;
        this.repositoryName = repositoryName;
    }

    public ZKConfig(String fileName) throws IOException {
        Properties properties=new Properties();
        InputStream inputStream = Object.class.getResourceAsStream("/"+fileName+".properties");
        properties.load(inputStream);
        this.serverIps=properties.getProperty("ZKServer");
        this.repositoryName=properties.getProperty("ZKRepository");
        this.timeOut=Integer.parseInt(properties.getProperty("TimeOut"));
    }

    public String getServerIps() {
        return serverIps;
    }

    public void setServerIps(String serverIps) {
        this.serverIps = serverIps;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

}
