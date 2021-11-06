package com.eipen.springcloud.zuul.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyPreFilter02 extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 20;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext=RequestContext.getCurrentContext();
        boolean b = requestContext.sendZuulResponse();
        return b;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        System.err.println(requestURI);
        HttpServletResponse response = requestContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
//        try {
//            response.getWriter().write("偶欧欧欧欧欧欧欧欧");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        requestContext.setResponseBody("非吉安市劳动法时代峻峰傻掉了非吉安市领导加上来得及房间爱上即发生的纠纷as");
        return null;
    }
}
