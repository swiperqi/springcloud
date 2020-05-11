package com.qiqi.springcloudconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // 开启Feign，不添加这个注解使用@FeignClient启动会报错
public class SpringcloudConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsumerApplication.class, args);
    }
}
