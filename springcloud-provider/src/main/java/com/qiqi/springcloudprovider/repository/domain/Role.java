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
public class Role {

    private Long id;

    private String role;

    private List<Permission> permissions;
}
