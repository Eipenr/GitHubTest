package com.qianfeng.springbootssm.pojo;


import java.util.Date;

public class User {

  private long id;
  private String username;
  private String password;
  private Date lastlogin;
  private Date currentlogin;
  private String type;
  private String status;
  private String data2;
  private String data1;
  private Date regdate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin(Date lastlogin) {
    this.lastlogin = lastlogin;
  }

  public Date getCurrentlogin() {
    return currentlogin;
  }

  public void setCurrentlogin(Date currentlogin) {
    this.currentlogin = currentlogin;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getData2() {
    return data2;
  }

  public void setData2(String data2) {
    this.data2 = data2;
  }

  public String getData1() {
    return data1;
  }

  public void setData1(String data1) {
    this.data1 = data1;
  }

  public Date getRegdate() {
    return regdate;
  }

  public void setRegdate(Date regdate) {
    this.regdate = regdate;
  }
}
