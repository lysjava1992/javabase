package learn.netflix.eureka.consumer.feign;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableFeignClients
 * 启动 Feign的注解扫描
 * 只在服务调用方开启即可
 *
 * @EnableHystrix
 * 必须则yml中开启feign的熔断
 * 熔断降级
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class LearnNetflixEurekaConsumerFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaConsumerFeignHystrixApplication.class,args);
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
