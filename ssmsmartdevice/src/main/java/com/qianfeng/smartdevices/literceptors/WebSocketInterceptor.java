package com.qianfeng.smartdevices.literceptors;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    // /websocket/zhangsan
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //我们约定，请求建立连接的时候请求的最后一位就是唯一标识
        //先获取请求的地址
        String uri = request.getURI().toString();
        String name = uri.substring(uri.lastIndexOf("/") + 1);
        attributes.put("name",name);//保存一个可以识别会话的唯一数据进去，key是无所谓，只要后边获取到的是这个名字就行
        return super.beforeHandshake(request,response,wsHandler,attributes);

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
