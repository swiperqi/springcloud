package com.qiqi.springcloudzuul.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiqi
 * @date 2020-05-15
 */
@Configuration
public class ZuulFilterConfig {
    /**
     * 没有
     */
    @Bean
    public ZuulAccessFilter zuulFilter() {
        return new ZuulAccessFilter();
    }
}
