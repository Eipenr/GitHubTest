package com.qianfeng.smartdevices.service.impl;

import com.github.pagehelper.PageHelper;
import com.qianfeng.smartdevices.mapper.HumitureMapper;
import com.qianfeng.smartdevices.pojo.Humiture;
import com.qianfeng.smartdevices.service.HumitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumitureServiceImpl implements HumitureService {

    private HumitureMapper humitureMapper;

    @Autowired
    public void setHumitureMapper(HumitureMapper humitureMapper) {
        this.humitureMapper = humitureMapper;
    }

    @Override
    public List<Humiture> findAllHumiture() {
        PageHelper.startPage(1, 15);
        List<Humiture> allHumiture = humitureMapper.findAllHumiture();
        return allHumiture;
    }
}
