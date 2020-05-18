package com.qiqi.springcloudsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author qiqi
 * @date 2020-05-15
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/getAccessToken")
    public Principal getAccessToken(Principal principal) {
        return principal;
    }
}
