package com.qianfeng.smartdevices.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogData {

    private Long id;

    private String parmas;

    private String result;

    private String user;


    private Date date;

    private String caozuo;//正在干什么

    private int code;

    private String msg;



    public LogData(){
        this.date=new Date();
    }

    public LogData(Long id, String parmas, String result, String user, Date date, String caozuo, int code, String msg) {
        this.id = id;
        this.parmas = parmas;
        this.result = result;
        this.user = user;
        this.date = date;
        this.caozuo = caozuo;
        this.code = code;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParmas() {
        return parmas;
    }

    public void setParmas(String parmas) {
        this.parmas = parmas;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(String caozuo) {
        this.caozuo = caozuo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LogData{" +
                "id=" + id +
                ", parmas='" + parmas + '\'' +
                ", result='" + result + '\'' +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", caozuo='" + caozuo + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
