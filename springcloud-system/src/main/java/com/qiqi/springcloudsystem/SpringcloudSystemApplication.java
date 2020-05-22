package com.qiqi.springcloudsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author qiqi
 * @date 2020-05-15
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableResourceServer
public class SpringcloudSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSystemApplication.class, args);
    }
}
