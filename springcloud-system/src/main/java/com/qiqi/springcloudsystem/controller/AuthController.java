package com.qiqi.springcloudsystem.controller;

import com.qiqi.springcloudcommon.configuration.AuthConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqi
 * @date 2020-05-15
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        return AuthConstant.ACCESS_TOKEN;
    }
}
