package com.qianfeng.smartdevices.pojo;


public class Humiture {

  private Long id;
  private String deviceUuid;
  private Double humidity;
  private Double temperature;
  private java.util.Date uploaddate;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getDeviceUuid() {
    return deviceUuid;
  }

  public void setDeviceUuid(String deviceUuid) {
    this.deviceUuid = deviceUuid;
  }


  public Double getHumidity() {
    return humidity;
  }

  public void setHumidity(Double humidity) {
    this.humidity = humidity;
  }


  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }


  public java.util.Date getUploaddate() {
    return uploaddate;
  }

  public void setUploaddate(java.util.Date uploaddate) {
    this.uploaddate = uploaddate;
  }

}
