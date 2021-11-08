package com.qianfeng.smartdevices.mapper;

import com.qianfeng.smartdevices.pojo.LogData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper {

    @Insert("insert into logs (parmas,result,user,`date`,caozuo,code,msg) values (#{parmas},#{result},#{user},#{date},#{caozuo},#{code},#{msg})")
    void addLog(LogData logData);

    @Select("select * from logs")
    List<LogData> findAllLogData();

}
