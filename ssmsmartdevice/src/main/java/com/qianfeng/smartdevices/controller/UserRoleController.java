package com.qianfeng.smartdevices.controller;

import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {

    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping ("/userrole")
    public R findRoleByUserId(Long uid){
        List<Long> roleByUserId = userRoleService.findRoleByUserId(uid);
        return R.setOK(roleByUserId);
    }

    @PostMapping("/userrole")
    public R addRoleByUserId(@RequestParam Long uid,@RequestParam List<Long> rids){
        userRoleService.addRoleByUserId(uid,rids);
        return R.setOK();
    }

}
