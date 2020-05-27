package com.qiqi.springcloudprovider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@Api(tags = "provider")
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello/{name}")
    // 需要provider角色才能访问
    @PreAuthorize("hasAuthority('ROLE_provider')")
    @ApiOperation(value = "provider-hello", httpMethod = "GET")
    public String hello(@PathVariable @ApiParam("name") String name) {
        return "provider" + port + ": </br>hello, " + name;
    }
}
