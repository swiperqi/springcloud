package com.qiqi.springcloudsystem.controller;

import com.qiqi.springcloudcommon.configuration.AuthConstant;
import com.qiqi.springcloudcommon.dto.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-15
 */

@RestController
@RequestMapping("/auth")
@Api(tags = "auth")
@Slf4j
public class AuthController {

    @GetMapping("/principal")
    @ApiOperation(value = "获取当前用户的信息", httpMethod = "GET")
    public Principal principal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_system')")
    @ApiOperation(value = "使用system权限访问user接口", httpMethod = "GET")
    public List<UserInfo> user() {
        return AuthConstant.USER_INFO_LIST;
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "GET")
    public void login(@ApiParam(name = "client_id", defaultValue = "provider") @RequestParam String client_id,
                      @ApiParam(name = "client_secret", defaultValue = "provider") @RequestParam String client_secret,
                      @ApiParam(name = "grant_type", defaultValue = "password") @RequestParam String grant_type,
                      @ApiParam(name = "username", defaultValue = "provider") @RequestParam String username,
                      @ApiParam(name = "password", defaultValue = "provider") @RequestParam String password,
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        log.info("调用/oauth/token,client_id:{}, client_secret:{}, grant_type:{}, username:{}, password: {}",
                client_id, client_secret, grant_type, username, password);
        request.getRequestDispatcher("/oauth/token").include(request, response);
    }
}
