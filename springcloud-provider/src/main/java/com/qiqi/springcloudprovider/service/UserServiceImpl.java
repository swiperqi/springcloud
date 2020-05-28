package com.qiqi.springcloudprovider.service;

import com.qiqi.springcloudprovider.repository.domain.Permission;
import com.qiqi.springcloudprovider.repository.domain.Role;
import com.qiqi.springcloudprovider.repository.domain.User;
import com.qiqi.springcloudprovider.repository.mapper.PermissionMapper;
import com.qiqi.springcloudprovider.repository.mapper.RoleMapper;
import com.qiqi.springcloudprovider.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-28
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User selectByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        roles.forEach(role -> {
            List<Permission> permissions = permissionMapper.selectByRoleId(role.getId());
            role.setPermissions(permissions);
        });
        user.setRoles(roles);

        return user;
    }
}
