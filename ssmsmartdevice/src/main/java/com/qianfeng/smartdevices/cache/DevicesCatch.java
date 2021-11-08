package com.qianfeng.smartdevices.cache;

import com.qianfeng.smartdevices.event.DevicesChangeEvent;
import com.qianfeng.smartdevices.mapper.DevicesMapper;
import com.qianfeng.smartdevices.pojo.Devices;
import com.qianfeng.smartdevices.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DevicesCatch extends BaseCache<Long, Devices, DevicesChangeEvent>{

    @Autowired
    private DevicesMapper devicesMapper;


    @Override
    public void initData() {

        List<Devices> allData=getAllData();
        List<Devices> allDevices = devicesMapper.findAllDevices();
        allData.clear();
        allData.addAll(allDevices);
        Map<Long, Devices> valueMap = getValueMap();
        valueMap.clear();
        allDevices.forEach(devices -> valueMap.put(devices.getId(),devices));
    }

    @Override
    public void onEvent(DevicesChangeEvent event) {
        super.onEvent(event);
    }
}
