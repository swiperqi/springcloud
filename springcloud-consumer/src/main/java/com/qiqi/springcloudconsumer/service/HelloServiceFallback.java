package com.qiqi.springcloudconsumer.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {

    /**
     * 远程调用失败或者超时才会执行这个方法
     */
    @Override
    public String hello(String name) {
        return name + ", method hello() timeout";
    }

    @Override
    public String world() {
        return "method world() timeout";
    }
}
