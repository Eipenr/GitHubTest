package com.qianfeng.smartdevices.service;


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.event.LogsEvent;
import com.qianfeng.smartdevices.pojo.LogData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogService {

    //    void addLog(LogData logData);
    void onEvent(LogsEvent event);

    PageInfo<LogData> findAllLogs(@Param("page") int page, @Param("limit") int limit);

}
