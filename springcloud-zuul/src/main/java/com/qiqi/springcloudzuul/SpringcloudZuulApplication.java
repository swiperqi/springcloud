package com.qiqi.springcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author qiqi
 * @date 2020-05-13
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy //开启zuul
public class SpringcloudZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulApplication.class, args);
    }
}
