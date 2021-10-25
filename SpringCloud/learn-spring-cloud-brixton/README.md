# SpringCloud基础教程

### brixton目录

  `learn-spring-cloud-brixton`

  版本

```xml
	<!--SpringBoot 版本-->
  <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.3.5.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<!--SpringCloud 版本-->
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Brixton.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```



##### learn-brixton-eureka-server

```
注册中心
```

##### learn-brixton-eureka-producer

```
注册客户端-》在注册中心注册
服务提供者
```

##### learn-brixton-ribbon-customer

```
消费端(也是注册中心的一个客户端) ribbon+eureka 实现多个服务提供者时的负载
熔断器，需要额外引入hystrix
```

##### learn-brixton-feign-customer

```
消费端 feign+eureka 其中feign也用ribbon做负载，也支持hystrix，不需要额外引入
Feign是一个声明式的Web Service客户端，它使得编写Web Serivce客户端变得更加简单
```

##### learn-brixton-config-server

```
配置中心 配置文件统一管理
消息总线 通过rabbitmq通知其他服务配置改变
```

##### learn-brixton-config-client

```
读取bootstrap->注册->查询config-server地址->请求application配置文件->加载
```

##### learn-brixton-zuul

```
服务网关 请求路由/请求过滤/认证
```

