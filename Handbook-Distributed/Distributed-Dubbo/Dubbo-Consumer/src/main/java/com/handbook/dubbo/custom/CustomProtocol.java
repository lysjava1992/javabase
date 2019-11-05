package com.handbook.dubbo.custom;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @description:  自定义扩展了Protocol 利用dubbo的SPI 替换了dubbo的默认组件
 * @author: Mr.Luan
 * @create: 2019-11-05 16:42
 **/
public class CustomProtocol implements Protocol {
    public int getDefaultPort() {
        System.out.print("自定义-----------");
        return 8011;
    }

    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        System.out.print("自定义-----------");
        return null;
    }

    public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
        System.out.print("自定义-----------");
        return null;
    }

    public void destroy() {
        System.out.print("自定义-----------");

    }
}
