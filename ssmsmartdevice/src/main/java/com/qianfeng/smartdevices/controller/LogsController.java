package com.qianfeng.smartdevices.controller;


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.event.LogsEvent;
import com.qianfeng.smartdevices.pojo.LogData;
import com.qianfeng.smartdevices.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/logs")
public class LogsController {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/logs")
    public R findAllLogs(int page,int limit){

        PageInfo<LogData> allLogs = logService.findAllLogs(page,limit);

            return R.setOK(allLogs);
    }




}
