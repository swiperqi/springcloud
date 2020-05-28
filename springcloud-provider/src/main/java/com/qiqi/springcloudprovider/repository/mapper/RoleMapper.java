package com.qiqi.springcloudprovider.repository.mapper;

import com.qiqi.springcloudprovider.repository.domain.Role;

import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-28
 */

public interface RoleMapper {

    Role selectById(Long id);

    List<Role> selectByUserId(Long userId);
}
