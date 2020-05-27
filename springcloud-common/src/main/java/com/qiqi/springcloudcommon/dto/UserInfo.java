package com.qiqi.springcloudcommon.dto;

import lombok.Data;

/**
 * @author qiqi
 * @date 2020-05-18
 */
@Data
public class UserInfo {

    private String username;

    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo() {}

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
