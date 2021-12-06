package learn.netflix.eureka.consumer.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService {
    /**
     * Ribbon 提供了基于服务名称进行REST服务间调用
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * @HystrixCommand(fallbackMethod = "fallback")
     *  配置断路器，在目标服务掉线时执行本地方法
     *
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Map info() {
        return  restTemplate.getForObject("http://Eureka-Client/info",Map .class);
    }

    /**
     *  @HystrixCommand应用于线程资源级别，而不是服务级别
     *   info() 会因为延迟走本地方法
     *   info2() 则不会
     *   虽然两个方法对应的是同一个服务，但对应不同的资源
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public Map info2() {
        return  restTemplate.getForObject("http://Eureka-Client/info2",Map .class);
    }

    public Map fallback(){
        Map<String,String>map=new HashMap<>();
        map.put("status","ERR");
        map.put("msg","对应服务不在线");
        return map;
    }


}
