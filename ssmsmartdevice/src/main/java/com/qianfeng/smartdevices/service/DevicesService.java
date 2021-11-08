package com.qianfeng.smartdevices.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.event.UpdateDeviceStatusEvent;
import com.qianfeng.smartdevices.pojo.Devices;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DevicesService {

    PageInfo<Devices> findAllDevices(int page,int limit,String address,String status,Long categoryid,Long areaid);

    void addDevice(Devices devices);

    void updateDevice(Devices devices);

    void deleteDevice(List<Long> ids);


    void sendCommand(String uuid, String command);

    void updateDeviceStatus(String uuid,String status);

    void onEvent(UpdateDeviceStatusEvent event);
}
