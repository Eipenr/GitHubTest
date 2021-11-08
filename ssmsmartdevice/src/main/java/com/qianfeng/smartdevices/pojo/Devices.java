package com.qianfeng.smartdevices.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

public class Devices implements CheckNull{

  private Long id;
  private String devicesuuid;
  private String devicename;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private java.util.Date addtime;
  private String address;
  private Long areaid;
  private String direction;
  private Long num;
  private String status;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private java.util.Date lastconnecttime;
  private Long categoryid;

  private String areaname;
  //冗余数据,记录所属的区域的名字
  private String categoryname;//分类名字,冗余数据

  private Categories category;

  public Categories getCategory() {
    return category;
  }

  public void setCategory(Categories category) {
    this.category = category;
  }

  public String getAreaname() {
    return areaname;
  }

  public void setAreaname(String areaname) {
    this.areaname = areaname;
  }

  public String getCategoryname() {
    return categoryname;
  }

  public void setCategoryname(String categoryname) {
    this.categoryname = categoryname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getDevicesuuid() {
    return devicesuuid;
  }

  public void setDevicesuuid(String devicesuuid) {
    this.devicesuuid = devicesuuid;
  }


  public String getDevicename() {
    return devicename;
  }

  public void setDevicename(String devicename) {
    this.devicename = devicename;
  }


  public java.util.Date getAddtime() {
    return addtime;
  }

  public void setAddtime(java.util.Date addtime) {
    this.addtime = addtime;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public Long getAreaid() {
    return areaid;
  }

  public void setAreaid(Long areaid) {
    this.areaid = areaid;
  }


  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }


  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.util.Date getLastconnecttime() {
    return lastconnecttime;
  }

  public void setLastconnecttime(java.util.Date lastconnecttime) {
    this.lastconnecttime = lastconnecttime;
  }


  public Long getCategoryid() {
    return categoryid;
  }

  public void setCategoryid(Long categoryid) {
    this.categoryid = categoryid;
  }

  @Override
  public boolean isNull(CheckStatus status) {
    switch (status){
      case ADD:
        return devicesuuid==null&&devicename==null&&address==null&&areaid==null&&direction==null&&num==null&&this.status==null;
      case UPDATE:
        return areaid==-100;
    }
    return false;
  }
}
