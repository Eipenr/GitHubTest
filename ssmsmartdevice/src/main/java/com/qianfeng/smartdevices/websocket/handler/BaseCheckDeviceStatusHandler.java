package com.qianfeng.smartdevices.websocket.handler;

import com.qianfeng.smartdevices.event.CheckDeviceStatusEvent;
import com.qianfeng.smartdevices.event.UpdateDeviceStatusEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface BaseCheckDeviceStatusHandler {

    ApplicationContext getContext();
    Map<Object, WebSocketSession> getAllClients();


    @EventListener
    @Async
    default void onEvent(CheckDeviceStatusEvent event){

        getAllClients().forEach((uuid,session)->{
            try {
                System.err.println(uuid+"设备在线");
                session.sendMessage(new TextMessage(""));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("设备已经离线了"+uuid);
                getContext().publishEvent(new UpdateDeviceStatusEvent((String) uuid,"0"));
                getAllClients().remove(uuid);
            }


        });

    }

}
