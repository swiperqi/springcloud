package com.qiqi.springcloudprovider.repository.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qiqi
 * @date 2020-05-28
 */

@Data
@Accessors(chain = true)
public class Permission {

    private Long id;

    private String permission;
}
