package com.qiqi.springcloudprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello/{name}")
    @PreAuthorize("hasAuthority('ROLE_provider')")
    public String hello(@PathVariable String name) {
        return "provider" + port + ": </br>hello, " + name;
    }
}
