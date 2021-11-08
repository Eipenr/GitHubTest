package com.qianfeng.smartdevices.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.event.LogsEvent;
import com.qianfeng.smartdevices.mapper.LogMapper;
import com.qianfeng.smartdevices.pojo.LogData;
import com.qianfeng.smartdevices.service.LogService;
import javafx.scene.effect.SepiaTone;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    private LogMapper logMapper;




    @Autowired
    public void setLogMapper(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @EventListener
    @Async
    public void onEvent(LogsEvent event){

        if (event.getLogData().getCaozuo()!=null) {
            logMapper.addLog(event.getLogData());
        }
//        return event.getLogData();
    }

    @Override
    public PageInfo<LogData> findAllLogs(int page, int limit) {
        PageHelper.startPage(page,limit);
        return new PageInfo<>(logMapper.findAllLogData());
    }


}
