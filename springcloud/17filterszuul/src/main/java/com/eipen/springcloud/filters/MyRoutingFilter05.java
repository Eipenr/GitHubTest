package com.eipen.springcloud.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class MyRoutingFilter05 extends ZuulFilter {
    /**
     * 返回过滤器的内容
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    /**
     * 执行的顺序,在类型相同的情况下，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 开启或者关闭这个过滤器
     * true  开启
     * false 关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        boolean b = requestContext.sendZuulResponse();
        return b;
    }

    /**
     * 如果过滤器开始了，则会执行该方法
     * @return  返回值无意义，不会返回给用户
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.err.println("MyRoutingFilter执行了");
        return null;
    }
}
