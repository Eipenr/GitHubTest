package com.qianfeng.smartdevices.event;

import com.qianfeng.smartdevices.pojo.LogData;

public class LogsEvent {

    private LogData logData;


    public LogsEvent(LogData logData) {
        this.logData = logData;
    }

    public LogData getLogData() {
        return logData;
    }

    public void setLogData(LogData logData) {
        this.logData = logData;
    }
}
