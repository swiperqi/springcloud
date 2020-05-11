package com.qiqi.springcloudconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用FeignClient注解来调用其他服务的方法，
 * name：要调用的服务的name，在eureka页面是Application，
 * fallback：该接口的实现方法，如果调不通且开启了熔断，会执行fallback里的实现方法
 */
@FeignClient(name = "springcloud-provider", fallback = HelloServiceFallback.class)
@Service("helloService")
public interface HelloService {

    @GetMapping("/provider/hello/{name}")
    String hello(@PathVariable("name") String name);

    @GetMapping("/provider/world")
    String world();
}
