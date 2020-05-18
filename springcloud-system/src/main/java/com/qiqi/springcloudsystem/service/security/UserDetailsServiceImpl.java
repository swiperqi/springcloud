package com.qiqi.springcloudsystem.service.security;

import com.qiqi.springcloudcommon.dto.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.qiqi.springcloudcommon.configuration.AuthConstant.USER_INFO_LIST;

/**
 * @author qiqi
 * @date 2020-05-18
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserInfo user : USER_INFO_LIST) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
