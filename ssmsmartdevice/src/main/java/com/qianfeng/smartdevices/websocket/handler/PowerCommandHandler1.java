package com.qianfeng.smartdevices.websocket.handler;

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

//@Component
public class PowerCommandHandler1 extends TextWebSocketHandler {

    //我们需要一个map来保存集合
    private Map<Object,WebSocketSession> allClients=new HashMap<>();

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }




    /**
     * 连接后执行
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Object name = session.getAttributes().get("name");

        //拿到会话session 中的用户的唯一标识
        WebSocketSession socketSession = allClients.get(name);
        if(socketSession!=null){//移除之前的会话，在断线重连的时候进行使用
            try {
                socketSession.close();
                allClients.remove(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.err.println("继电器控制器建立 websocket 连接了，标识 ====> "+ name);
        allClients.put(name,session);//以用户唯一标识作为key，将会话保存的 map 中
        //更新设备在线状态
        context.publishEvent(new UpdateDeviceStatusEvent((String) name,"1"));


    }

    /**
     * 收到文本消息的时候执行
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        System.err.println("收到设备发来的消息："+ payload);



    }

    /**
     * 连接关闭后执行
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);

        Object name = session.getAttributes().get("name");
        //设备下线后更新设备状态为下线状态
        System.err.println("设备已经下线");
        context.publishEvent(new UpdateDeviceStatusEvent((String) name,"0"));
        allClients.remove(name);
    }


    /**
     * 当业务中发送控制命令的事件的时候 会执行当前方法
     *
     * @param event
     */
    @EventListener
    @Async//通过异步执行事件中的操作,因为命令可以发送失败,我们的业务只需要通知发送操作即可,就可以返回给客户端成功的结果
    public void onEvent(PowerCommandEvent event) throws IOException {
        System.err.println(Thread.currentThread().getName());
        String uuid = event.getUuid();//设备的唯一标识
        WebSocketSession socketSession = allClients.get(uuid);
        if (socketSession != null && socketSession.isOpen()) {
            System.err.println("向设备发送了参数"+event.getCommand());
            socketSession.sendMessage(new TextMessage(event.getCommand()));//发送数据
        }
    }

    @EventListener
    @Async
    public void onEvent(CheckDeviceStatusEvent event) {
        //检查设备状态就是遍历所有的在线设备,看看谁不在,
        //怎么知道谁不在,发送一个消息,出现异常就说明不在了

        allClients.forEach((uuid, session) -> {

            try {
                session.sendMessage(new TextMessage("11"));
            } catch (Exception e) {
                e.printStackTrace();
                //设备离线了
//              try {
//                  session.close();
//              } catch (IOException ex) {
//                  ex.printStackTrace();
//              }
                System.err.println("设备编号为" + uuid + "已经离线了");
                context.publishEvent(new UpdateDeviceStatusEvent((String) uuid, "0"));
                allClients.remove(uuid);
            }

        });
    }
}
