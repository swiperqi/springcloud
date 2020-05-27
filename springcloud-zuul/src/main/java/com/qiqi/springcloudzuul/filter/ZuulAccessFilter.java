package com.qiqi.springcloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

/**
 * @author qiqi
 * @date 2020-05-15
 */

public class ZuulAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行过滤器，这里可以设置过滤哪些请求
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        // oauth/token请求、swagger不过滤
        if (requestURI.contains("oauth/token") ||
                requestURI.contains("swagger") ||
                requestURI.contains("v2") ||
                requestURI.contains("auth/login")) {
            return false;
        }
        return true;
    }

    /**
     * 设置过滤规则，这里设置的规则为请求头里需要有Authorization或者带有access_token参数，
     * 请求头里没有Authorization或者没有access_token参数
     * 都不会进行路由，被拦截
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String authorization = ctx.getRequest().getHeader("Authorization");
        String accessToken = ctx.getRequest().getParameter("access_token");
        if (StringUtils.isBlank(accessToken) && StringUtils.isBlank(authorization)) {
            // 设置不进行路由
            ctx.setSendZuulResponse(false);
            // 设置返回码
            ctx.setResponseStatusCode(401);
            // 设置返回内容
            ctx.setResponseBody("{\n\"code\":\"401\",\n\"content\":\"AccessToken is empty\"\n}");
        }
        return null;
    }
}
