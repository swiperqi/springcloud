package com.qiqi.springcloudprovider.service;

import com.qiqi.springcloudprovider.repository.domain.User;

/**
 * @author qiqi
 * @date 2020-05-28
 */

public interface UserService {

    User selectByUsername(String username);

}
