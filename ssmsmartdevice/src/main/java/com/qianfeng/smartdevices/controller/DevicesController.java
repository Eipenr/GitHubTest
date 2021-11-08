package com.qianfeng.smartdevices.controller;


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.Devices;
import com.qianfeng.smartdevices.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/devices")
public class DevicesController {

    private DevicesService devicesService;

    @Autowired
    public void setDevicesService(DevicesService devicesService) {
        this.devicesService = devicesService;
    }


    @GetMapping ("/devices")
    @AopLogAnnotation("查找所有的设备")
    public R findAllDevices( int page,int limit,String address,String status,Long categoryid,Long areaid){
        PageInfo<Devices> allDevices = devicesService.findAllDevices(page, limit,address,status,categoryid,areaid);
        return R.setOK(allDevices);
    }

    @PostMapping("/device")
    @AopLogAnnotation("添加设备")
    public R addDevice(@RequestBody Devices devices){
        devicesService.addDevice(devices);
        return R.setOK();
    }

    @PutMapping("/device")
    @AopLogAnnotation("更新设备")
    public R updateDevice(@RequestBody Devices devices){
        devicesService.updateDevice(devices);
        return R.setOK();
    }

    @DeleteMapping("/devices")
    @AopLogAnnotation("删除设备")
    public R deleteDevice(@RequestParam  List<Long> ids ){
        devicesService.deleteDevice(ids);
        return R.setOK();
    }

    @PostMapping("/command/{uuid}/{command}")
    @AopLogAnnotation("用户权限")
    public void sendCommand(@PathVariable String uuid,@PathVariable String command){
        devicesService.sendCommand(uuid,command);
    }



}
