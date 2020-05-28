package com.qiqi.springcloudprovider.repository.mapper;

import com.qiqi.springcloudprovider.repository.domain.Permission;

import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-28
 */

public interface PermissionMapper {

    Permission selectById(Long id);

    List<Permission> selectByRoleId(Long roleId);

}
