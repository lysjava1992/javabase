package learn.netflix.eureka.consumer.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 此处
 *  @EnableDiscoveryClient 和 @EnableEurekaClient作用相同
 *  @EnableDiscoveryClient是SpringCloud提供的服务发现更高一层的抽象
 *  @EnableEurekaClient只能用来向Eureka注册
 *  @EnableDiscoveryClient可以向Eureka注册，也可以向consul注册
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LearnNetflixEurekaConsumerRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixEurekaConsumerRibbonApplication.class,args);
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
