package com.qianfeng.smartdevices.websocket.handler;

import com.qianfeng.smartdevices.event.ColorCommandEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Component
public class ColorCommandHandler1 extends TextWebSocketHandler {

    private Map<Object,WebSocketSession> allClients=new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object name = session.getAttributes().get("name");
        System.err.println("颜色控制建立 websocket 请求了，标识是"+name);
        allClients.put(name,session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.err.println("收到颜色控制的消息："+message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @EventListener
    @Async
    public void onEvent(ColorCommandEvent event) throws IOException {

        System.err.println("收到了颜色的指令"+event.getCommand());
        WebSocketSession socketSession = allClients.get(event.getUuid());
        if (socketSession!=null&& socketSession.isOpen()){
            socketSession.sendMessage(new TextMessage(event.getCommand()));
        }
    }

}
