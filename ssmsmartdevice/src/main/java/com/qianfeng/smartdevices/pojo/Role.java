package com.qianfeng.smartdevices.pojo;


import org.springframework.util.ObjectUtils;

import java.util.List;

public class Role implements CheckNull{

  private Long id;
  private String name;
  private String remark;
  private Long status;

  private List<Menu> menuList;

  public List<Menu> getMenuList() {
    return menuList;
  }

  public void setMenuList(List<Menu> menuList) {
    this.menuList = menuList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  @Override
  public boolean isNull(CheckStatus status) {

    switch (status){
      case ADD:
        return ObjectUtils.isEmpty(name)||ObjectUtils.isEmpty(remark)||ObjectUtils.isEmpty(status);
      case UPDATE:
        return (id==null||id<=0)||(ObjectUtils.isEmpty(name)&&ObjectUtils.isEmpty(remark)&&ObjectUtils.isEmpty(status));
    }
    return false;
  }
}
