package com.qianfeng.smartdevices.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.cache.AreasCache;
import com.qianfeng.smartdevices.cache.CategoriesCache;
import com.qianfeng.smartdevices.cache.DevicesCatch;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.event.ColorCommandEvent;
import com.qianfeng.smartdevices.event.DevicesChangeEvent;
import com.qianfeng.smartdevices.event.PowerCommandEvent;
import com.qianfeng.smartdevices.event.UpdateDeviceStatusEvent;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.DeleteErrorException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.DevicesMapper;
import com.qianfeng.smartdevices.pojo.Areas;
import com.qianfeng.smartdevices.pojo.Categories;
import com.qianfeng.smartdevices.pojo.CheckStatus;
import com.qianfeng.smartdevices.pojo.Devices;
import com.qianfeng.smartdevices.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.qianfeng.smartdevices.dto.ResultCode.DATA_NOT_ALLOW_NUL;

@Service
@Transactional
public class DevicesServiceImpl implements DevicesService {

    private DevicesMapper devicesMapper;
    private AreasCache areasCache;
    private CategoriesCache categoriesCache;
    private DevicesCatch devicesCatch;
    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setDevicesCatch(DevicesCatch devicesCatch) {
        this.devicesCatch = devicesCatch;
    }

    @Autowired
    public void setCategoriesCache(CategoriesCache categoriesCache) {
        this.categoriesCache = categoriesCache;
    }

    @Autowired
    public void setAreasCache(AreasCache areasCache) {
        this.areasCache = areasCache;
    }

    @Autowired
    public void setDevicesMapper(DevicesMapper devicesMapper) {
        this.devicesMapper = devicesMapper;
    }

    @Override
    public PageInfo<Devices> findAllDevices(int page, int limit, String address, String status, Long categoryid, Long areaid) {
        PageHelper.startPage(page, limit);
        List<Devices> allData = devicesCatch.getAllData();
        Map<Long, Areas> areasMap = areasCache.getValueMap();
        Map<Long, Categories> categoriesMap = categoriesCache.getValueMap();
        allData.forEach(devices -> {
                    devices.setAreaname(areasMap.get(devices.getAreaid()).getAreaname());
                    devices.setCategoryname(categoriesMap.get(devices.getCategoryid()).getCategoryname());
                    devices.setCategory(categoriesMap.get(devices.getCategoryid()));
                }
        );
        List<Devices> devicesList = allData.stream().filter(devices -> {
            boolean b1 = ObjectUtils.isEmpty(address) ? true : devices.getAddress().contains(address);
            boolean b2 = (ObjectUtils.isEmpty(status) || status.equals("-100")) ? true : devices.getStatus().equals(status);
            boolean b3 = (ObjectUtils.isEmpty(categoryid) || categoryid == -100) ? true : categoryid == devices.getCategoryid();
            boolean b4 = (ObjectUtils.isEmpty(areaid) || areaid == -100) ? true : areaid == devices.getAreaid();
            return b1 & b2 & b3 & b4;
        }).collect(Collectors.toList());
        List<Devices> collect = devicesList.stream().skip((page - 1) * limit).limit(limit).collect(Collectors.toList());
        PageInfo<Devices> pageInfo = new PageInfo<>(collect);
        pageInfo.setTotal(devicesList.size());
        return pageInfo;
    }

    @Override
    public void addDevice(Devices devices) {
        if (devices.isNull(CheckStatus.ADD)) {
            throw new AddErrorException("有信息没有填写", DATA_NOT_ALLOW_NUL);
        }
        devicesMapper.addDevice(devices);
        context.publishEvent(new DevicesChangeEvent());
    }

    @Override
    public void updateDevice(Devices devices) {
        if (devices.isNull(CheckStatus.UPDATE)) {
            throw new UpdateErrorException("地区需要重新选择，谢谢", DATA_NOT_ALLOW_NUL);
        }
        devicesMapper.updateDevice(devices);
        context.publishEvent(new DevicesChangeEvent());
    }

    @Override
    public void deleteDevice(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            throw new DeleteErrorException("请选择你要报废的设备", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        devicesMapper.deleteDevice(ids);
        context.publishEvent(new DevicesChangeEvent());
    }

    @Override
    public void sendCommand(String uuid, String command) {

        String value=null;
        //根据标识找到设备的会话
        switch (command) {
            case "open":
                value="0";
                break;
            case "close":
                value="1";
                break;
            case "change":
                value="2";
                context.publishEvent(new ColorCommandEvent(uuid,value));
                return;
        }
        //根据会话发送相应的命令
        System.err.println(Thread.currentThread().getName());
        context.publishEvent(new PowerCommandEvent(uuid,value));

    }

    @Override
    public void updateDeviceStatus(String uuid, String status) {
        devicesMapper.updateOffLine(uuid,status);
        context.publishEvent(new DevicesChangeEvent());
    }


    @EventListener
    @Override
    @Async
    public void onEvent(UpdateDeviceStatusEvent event){
        updateDeviceStatus(event.getUuid(),event.getStatus());
    }





}
