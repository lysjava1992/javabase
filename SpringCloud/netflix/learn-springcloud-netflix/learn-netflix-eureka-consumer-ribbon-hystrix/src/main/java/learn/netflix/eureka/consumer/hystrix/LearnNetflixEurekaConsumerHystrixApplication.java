package learn.netflix.eureka.consumer.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *  @EnableCircuitBreaker和@EnableHystrix效果相同 断路器
 *  @EnableDiscoveryClient 服务注册
 *
 *  @SpringCloudApplication 是一个组合注解，可以替代一下三个注解
 *   @SpringBootApplication
 *   @EnableDiscoveryClient
 *   @EnableCircuitBreaker
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class LearnNetflixEurekaConsumerHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaConsumerHystrixApplication.class,args);
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
