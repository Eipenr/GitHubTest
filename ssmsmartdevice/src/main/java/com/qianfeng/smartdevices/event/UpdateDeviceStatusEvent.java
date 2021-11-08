package com.qianfeng.smartdevices.event;

public class UpdateDeviceStatusEvent {

    private String uuid;
    private String status;

    public UpdateDeviceStatusEvent(String uuid, String status) {
        this.uuid = uuid;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
