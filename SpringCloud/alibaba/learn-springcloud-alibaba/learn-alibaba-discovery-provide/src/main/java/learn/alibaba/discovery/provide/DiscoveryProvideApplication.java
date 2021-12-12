package learn.alibaba.discovery.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryProvideApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryProvideApplication.class,args);
    }
}
