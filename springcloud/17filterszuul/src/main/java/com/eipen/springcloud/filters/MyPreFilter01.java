package com.eipen.springcloud.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyPreFilter01 extends ZuulFilter {
    /**
     * 返回过滤器的内容
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行的顺序,在类型相同的情况下，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        //TODO 如果过滤器需要强制转发服务，那么这个过滤器的 filterOrder 必须大于等于5 因为zuul 内部有其他的过滤器进行封装操作，我强制更改后会导致有些数据无法获取，最终导致空指针
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
        return true;
    }

    /**
     * 如果过滤器开始了，则会执行该方法
     * @return  返回值无意义，不会返回给用户
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.err.println("MyPreFilter01执行了");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String requestURI = request.getRequestURI();
        System.err.println(requestURI);
//        requestContext.setSendZuulResponse(false);//拦截请求
        HttpServletResponse response = requestContext.getResponse();
        requestContext.put("suibian","张三的猪丢了");
//        try {
//            response.getWriter().write("这个是拦截请求后返回的数据");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        response.setContentType("text/html;charset=utf-8");
//        requestContext.setResponseBody("这个是拦截请求后返回的数据");


        //TODO 前置通知可以拦截并转发请求
        //TODO 如果过滤器需要强制转发服务，那么这个过滤器的 filterOrder 必须大于等于5 因为zuul 内部有其他的过滤器进行封装操作，我强制更改后会导致有些数据无法获取，最终导致空指针
        requestContext.put(FilterConstants.SERVICE_ID_KEY,"12itemprovider-eureka");//将当前的请求强行转发到 12itemprovider-eureka 服务
        requestContext.put(FilterConstants.REQUEST_URI_KEY,"/items/item/1");//强制转发到这个网址
        return null;
    }
}
