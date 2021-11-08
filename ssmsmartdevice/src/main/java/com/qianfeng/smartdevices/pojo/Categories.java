package com.qianfeng.smartdevices.pojo;


import org.springframework.util.ObjectUtils;

public class Categories implements CheckNull{

  private Long id;
  private String categoryname;
  private String txcommand;
  private String rxcommand;
  private String commandname;
  private Long status;

  @Override
  public boolean isNull(CheckStatus status) {
    switch (status) {
      case ADD:
        return ObjectUtils.isEmpty(categoryname)||ObjectUtils.isEmpty(txcommand)||ObjectUtils.isEmpty(rxcommand)||ObjectUtils.isEmpty(commandname);
      case UPDATE:
        return (id == null || id <= 0)||(ObjectUtils.isEmpty(categoryname)&&ObjectUtils.isEmpty(txcommand)&&ObjectUtils.isEmpty(rxcommand)&&ObjectUtils.isEmpty(commandname));
    }
    return false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getCategoryname() {
    return categoryname;
  }

  public void setCategoryname(String categoryname) {
    this.categoryname = categoryname;
  }


  public String getTxcommand() {
    return txcommand;
  }

  public void setTxcommand(String txcommand) {
    this.txcommand = txcommand;
  }


  public String getRxcommand() {
    return rxcommand;
  }

  public void setRxcommand(String rxcommand) {
    this.rxcommand = rxcommand;
  }


  public String getCommandname() {
    return commandname;
  }

  public void setCommandname(String commandname) {
    this.commandname = commandname;
  }


  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

}
