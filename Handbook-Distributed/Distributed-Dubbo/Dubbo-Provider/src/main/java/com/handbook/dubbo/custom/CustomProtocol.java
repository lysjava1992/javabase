package com.handbook.dubbo.custom;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @description:  自定义扩展了Protocol 利用dubbo的SPI 替换了dubbo的默认组件
 * @author: Mr.Luan
 * @create: 2019-11-05 16:42
 **/
//@Adaptive
public class CustomProtocol implements Protocol {
    public int getDefaultPort() {
        System.out.println("自定义-----------getDefaultPort");
        return 8011;
    }

    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        System.out.print("自定义-----------export");
        return null;
    }

    public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
        System.out.print("自定义-----------refer");
        return null;
    }

    public void destroy() {
        System.out.print("自定义-----------");

    }
}
