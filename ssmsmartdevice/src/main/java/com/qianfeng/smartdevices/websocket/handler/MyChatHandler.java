package com.qianfeng.smartdevices.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

//@Component
public class MyChatHandler extends TextWebSocketHandler {

    private Map<Object, WebSocketSession> allClients= new HashMap<>();//保存所有会话的map，key 是用来定位用户的关键，value 就是会话


    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    /**
     * 连接建立之后就进行执行了
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.err.println("用户和服务器建立 websocket 请求了");
        Object name = session.getAttributes().get("name");
        allClients.put(name,session);//以用户的唯一标识作为key，将会话保存在map 中
    }

    /**
     * 收到文本消息的时候执行
     * @param session 发生消息的人
     * @param message 发送的内容
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        System.err.println("收到客户端发过来的请求是"+payload);
        Map map = objectMapper.readValue(payload, Map.class);//保存了数据的map
        //在这里进行一个约定，我们发送的都是json格式的数据 用 toUser 来代表收消息的人 用msg来代表发送的消息是什么
        Object toUser = map.get("toUser");
        WebSocketSession socketSession = allClients.get(toUser);
        if (socketSession !=null && socketSession.isOpen()){
            Object from = session.getAttributes().get("name");
            socketSession.sendMessage(new TextMessage("收到了来自"+from+"的消息内容是："+map.get("msg")));
        }else {

            session.sendMessage(new TextMessage("对方不在线"));
        }



    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
