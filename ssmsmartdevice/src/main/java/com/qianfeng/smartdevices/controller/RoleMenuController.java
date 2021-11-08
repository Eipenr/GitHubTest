package com.qianfeng.smartdevices.controller;

import com.alibaba.druid.sql.builder.UpdateBuilder;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/rolemenus")
public class RoleMenuController {

    private RoleMenuService roleMenuService;

    @Autowired
    public void setRoleMenuService(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @GetMapping("/rolemenu")
    public R findMenuByRoleId(@RequestParam Long rid){
        List<Long> roleIdList = roleMenuService.findMenuByRole(rid);
        return R.setOK(roleIdList);
    }

    @PostMapping("/rolemenus")
    public R addMenusToRole(@RequestParam Long role,@RequestParam List<Long> mids){
        roleMenuService.addMenuToRole(role,mids);
        return R.setOK();
    }

    @DeleteMapping ("/rolemenus")
    public R deleteRoleMenus(@RequestParam Long role,@RequestParam List<Long> mids){
        roleMenuService.deleteRoleMenus(role,mids);
        return R.setOK();
    }


}
