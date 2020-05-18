package com.qiqi.springcloudcommon.configuration;

import com.qiqi.springcloudcommon.dto.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiqi
 * @date 2020-05-15
 */

public class AuthConstant {
    public static String ACCESS_TOKEN = "adiojawkjdknskja";

    public static List<UserInfo> USER_INFO_LIST = new ArrayList<>(
            Arrays.asList(
                    new UserInfo("feign", "feign"),
                    new UserInfo("system", "system"),
                    new UserInfo("ribbon", "ribbon"),
                    new UserInfo("provider", "provider")
            )
    );
}
