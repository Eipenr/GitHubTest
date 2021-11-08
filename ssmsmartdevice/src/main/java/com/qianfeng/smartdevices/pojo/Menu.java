package com.qianfeng.smartdevices.pojo;


import org.springframework.util.ObjectUtils;

import java.util.Objects;

public class Menu  implements CheckNull{

  private Long id;
  private String name;
  private Long parentId;
  private String url;
  private String icon;
  private String perms;
  private Long type;
  private Long sort;


  @Override
  public boolean isNull(CheckStatus status) {
    switch (status){
      case ADD:
        return ObjectUtils.isEmpty(name)||ObjectUtils.isEmpty(url)||ObjectUtils.isEmpty(perms)||ObjectUtils.isEmpty(type);
      case UPDATE:
        return  (id == null || id <= 0)||(ObjectUtils.isEmpty(name)&&ObjectUtils.isEmpty(url)&&ObjectUtils.isEmpty(perms)&&ObjectUtils.isEmpty(type))&&ObjectUtils.isEmpty(parentId)&&ObjectUtils.isEmpty(sort)&&ObjectUtils.isEmpty(icon);
    }
    return false;
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


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }


  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }


  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Menu menu = (Menu) o;
    return name.equals(menu.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
