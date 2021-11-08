package com.qianfeng.smartdevices.task;

import com.qianfeng.smartdevices.event.CheckDeviceStatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckStatusTask {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Scheduled(fixedRate = 30000)
    public void checkDevicesStatus(){
        System.err.println("定时任务执行了");
        context.publishEvent(new CheckDeviceStatusEvent());
    }


}
