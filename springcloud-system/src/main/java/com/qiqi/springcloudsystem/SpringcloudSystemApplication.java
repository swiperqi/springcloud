package com.qiqi.springcloudsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author qiqi
 * @date 2020-05-15
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.qiqi.springcloudsystem.repository.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringcloudSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSystemApplication.class, args);
    }
}
