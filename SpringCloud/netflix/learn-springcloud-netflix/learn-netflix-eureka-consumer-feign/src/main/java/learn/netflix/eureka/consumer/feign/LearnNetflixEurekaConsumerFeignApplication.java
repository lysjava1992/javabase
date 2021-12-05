package learn.netflix.eureka.consumer.feign;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableFeignClients
 * 启动 Feign的注解扫描
 * 只在服务调用方开启即可
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LearnNetflixEurekaConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaConsumerFeignApplication.class,args);
    }

    /**
     * 用于服务间的调用
     *  负载均衡
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
