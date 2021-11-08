package com.qianfeng.smartdevices.controller;


import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.Humiture;
import com.qianfeng.smartdevices.service.HumitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/humitures")
public class HumitureController {

    private HumitureService humitureService;

    @Autowired
    public void setHumitureService(HumitureService humitureService) {
        this.humitureService = humitureService;
    }

    @RequestMapping("/humitures")
    @AopLogAnnotation("查看温湿度")
    public R findAllHumiture(){
        List<Humiture> allHumiture = humitureService.findAllHumiture();
        List<Double> temper = allHumiture.stream().map(Humiture::getTemperature).collect(Collectors.toList());
        List<Double> humi = allHumiture.stream().map(Humiture::getHumidity).collect(Collectors.toList());
        List<Date> time = allHumiture.stream().map(Humiture::getUploaddate).collect(Collectors.toList());
        System.err.println(temper);
        System.err.println(humi);
        System.err.println(time);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("temper",temper);
        map.put("humi",humi);
        map.put("times",time);
        return R.setOK(map);
    }


}
