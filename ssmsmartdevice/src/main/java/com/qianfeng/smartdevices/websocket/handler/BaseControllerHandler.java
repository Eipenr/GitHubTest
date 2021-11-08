package com.qianfeng.smartdevices.websocket.handler;

import com.qianfeng.smartdevices.event.BaseCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

//
@Component
public class BaseControllerHandler<E extends BaseCommandEvent> extends TextWebSocketHandler {

    //需要一个map保存会话
//    private Map<Object,WebSocketSession> allClients=new HashMap<>();
    private Map<Object,WebSocketSession> allClients;



    private ApplicationContext context;

    @Autowired
    public void setAllClients(Map<Object, WebSocketSession> allClients) {
        this.allClients = allClients;
    }

    public Map<Object, WebSocketSession> getAllClients() {
        return allClients;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ApplicationContext getContext() {
        return context;
    }

    //建立连接，保存会话的作用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Object name = session.getAttributes().get("name");
        WebSocketSession socketSession = allClients.get(name);
        if (socketSession!=null){
            //说明上次断开是异常的，没有进行会话的移除，需要先移除会话
            socketSession.close();
            allClients.remove(name);
        }
        System.err.println("控制器建立了连接"+name);
        allClients.put(name,session);
        System.err.println(allClients.hashCode()+"------------");
        onOpen(session);


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        System.err.println("收到控制发送过来的消息"+message.getPayload());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        Object name = session.getAttributes().get("name");
        allClients.remove(name);
        onClose(session);
    }


    @EventListener
    @Async
    public void onEvent(E event) throws IOException {

        String uuid = event.getUuid();
        String command = event.getCommand();

        WebSocketSession socketSession = allClients.get(uuid);
        System.err.println(allClients.hashCode()+"------------");
        if (socketSession!=null&&socketSession.isOpen()){
            System.err.println("设备收到的信息是："+command);
            socketSession.sendMessage(new TextMessage(command));
        }
    }

    protected void onOpen(WebSocketSession session) {

    }

    protected void onClose(WebSocketSession session) {

    }
}
