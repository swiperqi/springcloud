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
        // provider请求不过滤
        if (requestURI.contains("provider")) {
            return false;
        }
        return true;
    }

    /**
     * 设置过滤规则，这里设置的规则为请求头里需要有accessToken，请求头里没有accessToken或者accessToken未空
     * 都不会进行路由，被拦截
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String accessToken = ctx.getRequest().getHeader("accessToken");
        if (StringUtils.isBlank(accessToken)) {
            // 设置不进行路由
            ctx.setSendZuulResponse(false);
            // 设置返回码
            ctx.setResponseStatusCode(401);
            // 设置返回内容
            ctx.setResponseBody("{\"code\":\"401\",\n\"content\":\"no AccessToken\"}");
        }
        return null;
    }
}
