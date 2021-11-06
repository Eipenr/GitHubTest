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
public class MyPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        System.err.println(requestURI);
//        requestContext.setSendZuulResponse(false);
        HttpServletResponse response = requestContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
//        try {
//            response.getWriter().write("呕吼。。。。");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //TODO 拦截请求并转发
        requestContext.put(FilterConstants.SERVICE_ID_KEY,"02itemclient");
        requestContext.put(FilterConstants.REQUEST_URI_KEY,"/items/item/1");
//        requestContext.setResponseBody("zhangsanzhangsan");
        return null;
    }
}
