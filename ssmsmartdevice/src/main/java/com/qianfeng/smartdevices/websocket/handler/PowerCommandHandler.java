package com.qianfeng.smartdevices.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.smartdevices.event.CheckDeviceStatusEvent;
import com.qianfeng.smartdevices.event.PowerCommandEvent;
import com.qianfeng.smartdevices.event.UpdateDeviceStatusEvent;
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

@Component
public class PowerCommandHandler extends BaseControllerHandler<PowerCommandEvent> implements BaseCheckDeviceStatusHandler{


    @Override
    public void onEvent(PowerCommandEvent event) throws IOException {
        super.onEvent(event);
    }

    @Override
    protected void onOpen(WebSocketSession session) {
        Object name = session.getAttributes().get("name");

        getContext().publishEvent(new UpdateDeviceStatusEvent((String) name,"1"));
    }

    @Override
    protected void onClose(WebSocketSession session) {
        Object name = session.getAttributes().get("name");
        getContext().publishEvent(new UpdateDeviceStatusEvent((String) name,"0"));
    }
}
