package com.qianfeng.smartdevices.mapper;

import com.qianfeng.smartdevices.pojo.Devices;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface DevicesMapper {

    List<Devices> findAllDevices();


    void addDevice(Devices devices);

    void updateDevice(Devices devices);

    void deleteDevice(@Param("ids") List<Long> ids);

    @Update("UPDATE devices SET lastconnecttime = #{date} , status =1  WHERE devicesuuid = #{uuid}")
    void updateConnectionTime(@Param("uuid") String uuid, @Param("date") Date date);

    @Update("UPDATE devices SET  status =#{status}  WHERE devicesuuid = #{uuid}")
    void updateOffLine(@Param("uuid") String uuid,@Param("status") String status);


    @Select("SELECT * FROM devices devicesuuid =#{devicesuuid}")
    Devices getDeviceByUUId(String  uuid);

    @Select("SELECT categoryid FROM devices WHERE devicesuuid=#{uuid}")
    Long getCategoryIdByUUID(String uuid);

}
