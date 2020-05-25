package com.qiqi.springcloudconsumer.controller;

import com.qiqi.springcloudconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "consumer feign:</br>" + helloService.hello(name);
    }

    @GetMapping("/world")
    public String world() {
        return "consumer feign:</br>" + helloService.world();
    }
}
