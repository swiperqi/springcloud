package com.qiqi.springcloudprovider.repository.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-28
 */

@Data
@Accessors(chain = true)
public class User {
    private Long id;

    private String username;

    private String password;

    private List<Role> roles;
}
