package com.qianfeng.smartdevices.pojo;


import org.springframework.util.ObjectUtils;

public class Areas implements CheckNull {

    private Long id;
    private String areaname;
    private Long parentid;
    private Long status;

    private String parentname;


    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    @Override
    public boolean isNull(CheckStatus status) {
        switch (status) {

            case ADD:
                return ObjectUtils.isEmpty(areaname);
            case UPDATE:
                return (id <=0 ||id==null)||(ObjectUtils.isEmpty(areaname)&&ObjectUtils.isEmpty(parentid)&&ObjectUtils.isEmpty(this.status));
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }


    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

}
