package com.qiqi.springcloudprovider.repository.mapper;

import com.qiqi.springcloudprovider.repository.domain.User;

/**
 * @author qiqi
 * @date 2020-05-28
 */

public interface UserMapper {

    User selectById(Long id);

    User selectByUsername(String username);
}
