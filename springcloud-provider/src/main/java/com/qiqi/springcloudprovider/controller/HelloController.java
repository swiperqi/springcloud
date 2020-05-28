package com.qiqi.springcloudprovider.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
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
    public String hello(@PathVariable String name) {
        return "provider" + port + ": </br>hello, " + name;
    }

    // RequiresRoles 配置的多个角色必须同时拥有才能访问
//    @RequiresRoles({"admin", "user", "trial"})
    @RequiresPermissions("read")
    @GetMapping("read")
    public String read() {
        return "read: admin, user, trial";
    }

//    @RequiresRoles({"admin", "user"})
    @RequiresPermissions("write")
    @GetMapping("write")
    public String write() {
        return "write: admin, user";
    }

//    @RequiresRoles({"admin"})
    @RequiresPermissions("update")
    @GetMapping("update")
    public String update() {
        return "update: admin";
    }

//    @RequiresRoles({"admin"})
    @RequiresPermissions("delete")
    @GetMapping("delete")
    public String delete() {
        return "delete: admin";
    }
}
