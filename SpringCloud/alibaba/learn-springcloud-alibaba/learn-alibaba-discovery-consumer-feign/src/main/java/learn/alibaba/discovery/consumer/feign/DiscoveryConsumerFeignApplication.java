package learn.alibaba.discovery.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryConsumerFeignApplication.class,args);
    }


}
