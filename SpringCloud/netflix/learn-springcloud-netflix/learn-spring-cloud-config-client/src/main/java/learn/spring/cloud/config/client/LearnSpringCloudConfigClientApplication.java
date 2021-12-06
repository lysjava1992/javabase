package learn.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class LearnSpringCloudConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnSpringCloudConfigClientApplication.class,args);
    }
}
