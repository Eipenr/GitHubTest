package com.qianfeng.smartdevices.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.ObjectUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements CheckNull {

  private Long id;
  private String username;
  private String password;
  private java.util.Date lastlogin;
  private java.util.Date currentlogin;
  private String type;
  private String status;
  private String data2;
  private String data1;
  private java.util.Date regdate;


  @Override
  public boolean isNull(CheckStatus status) {
    //我们这个操作判断数据是不是空的,我们在好几个地方比如增加 更新的时候都需要判断空不空
    //但是添加和更新判断的方式不一样, 怎么办?
    switch (status) {
      case ADD:
        return ObjectUtils.isEmpty(username)||ObjectUtils.isEmpty(password)||ObjectUtils.isEmpty(type);
      case UPDATE:
        return (id == null || id <= 0)||(ObjectUtils.isEmpty(password)&&ObjectUtils.isEmpty(type)&&ObjectUtils.isEmpty(this.status)&&ObjectUtils.isEmpty(data2)&&ObjectUtils.isEmpty(data1));
    }
    return false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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


  public java.util.Date getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin(java.util.Date lastlogin) {
    this.lastlogin = lastlogin;
  }


  public java.util.Date getCurrentlogin() {
    return currentlogin;
  }

  public void setCurrentlogin(java.util.Date currentlogin) {
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


  public java.util.Date getRegdate() {
    return regdate;
  }

  public void setRegdate(java.util.Date regdate) {
    this.regdate = regdate;
  }

}
