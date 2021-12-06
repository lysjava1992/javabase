package learn.netflix.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 *  Hystrix 仪表板；
 *  hystrix 查看仪表盘；在hystrix客户端应用使用/hystrix.stream监控
 *  @EnableTurbine 微服务聚合监控
 */
@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
@EnableDiscoveryClient
public class LearnNetflixHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnNetflixHystrixDashboardApplication.class,args);
    }
}
