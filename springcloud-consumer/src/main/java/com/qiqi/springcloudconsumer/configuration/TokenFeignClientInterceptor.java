package com.qiqi.springcloudconsumer.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qiqi
 * @date 2020-05-25
 */
@Component
public class TokenFeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            requestTemplate.header("Authorization", authorization);
        } else {
            String accessToken = request.getParameter("access_token");
            requestTemplate.header("Authorization", "Bearer " + accessToken);
        }

    }
}
