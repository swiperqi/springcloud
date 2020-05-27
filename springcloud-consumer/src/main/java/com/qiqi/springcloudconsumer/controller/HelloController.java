package com.qiqi.springcloudconsumer.controller;

import com.qiqi.springcloudconsumer.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
@Api(tags = "feign")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    @ApiOperation(value = "使用feign调用provider的hello", httpMethod = "GET")
    public String hello(@PathVariable("name") @ApiParam("name") String name) {
        return "consumer feign:</br>" + helloService.hello(name);
    }

    @GetMapping("/world")
    @ApiOperation(value = "访问provider中不存在的接口", httpMethod = "GET")
    public String world() {
        return "consumer feign:</br>" + helloService.world();
    }
}
