package com.qianfeng.smartdevices.controller;

import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.Role;
import com.qianfeng.smartdevices.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {


    private RoleService roleService;


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role")
    public R addRole(Role role) {
        roleService.addRole(role);
        return R.setOK();
    }

    @GetMapping("/roles")
    public R findAllRole() {
        List<Role> allRole = roleService.findAllRole();
        return R.setOK(allRole);
    }

    @DeleteMapping("/roles")
    public R deleteRole(@RequestParam List<Long> rid){
        roleService.deleteRole(rid);
        return R.setOK();
    }

    @PutMapping("/role")
    public R updateRole(Role role){
        roleService.updateRole(role);
        return R.setOK();
    }

}
