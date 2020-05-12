package com.qiqi.springcloudconsumerribbon.controller;

import com.qiqi.springcloudconsumerribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqi
 * @date 2020-05-12
 */
@RestController
@RequestMapping("/ribbon")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "consumer ribbon:</br>" + helloService.hello(name);
    }

    @GetMapping("/helloFeign/{name}")
    public String helloFeign(@PathVariable("name") String name) {
        return "consumer ribbon:</br>" + helloService.helloFeign(name);
    }
}
