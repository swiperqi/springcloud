package com.qiqi.springcloudprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan(basePackages = "com.qiqi.springcloudprovider.repository.mapper")
public class SpringcloudProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderApplication.class, args);
    }
}
