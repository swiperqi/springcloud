package com.qiqi.springcloudconsumerribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qiqi
 * @date 2020-05-12
 */

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudConsumerRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConsumerRibbonApplication.class, args);
    }
}
