package com.qianfeng.smartdevices.websocket.handler;

import com.qianfeng.smartdevices.event.ColorCommandEvent;
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
public class ColorCommandHandler extends BaseControllerHandler<ColorCommandEvent> {

    @Override
    public void onEvent(ColorCommandEvent event) throws IOException {
        super.onEvent(event);
    }
}
