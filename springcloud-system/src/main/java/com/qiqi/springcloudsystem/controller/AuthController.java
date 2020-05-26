package com.qiqi.springcloudsystem.controller;

import com.qiqi.springcloudcommon.configuration.AuthConstant;
import com.qiqi.springcloudcommon.dto.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-15
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/principal")
    public Principal principal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_system')")
    public List<UserInfo> user() {
        return AuthConstant.USER_INFO_LIST;
    }
}
