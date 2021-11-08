package com.qianfeng.smartdevices.service.impl;


//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼            BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  


import com.qianfeng.smartdevices.cache.RoleCache;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.MenusMapper;
import com.qianfeng.smartdevices.pojo.*;
import com.qianfeng.smartdevices.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jackiechan on 2021/10/11 14:40
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@Service
public class MenusServiceImpl implements MenusService {

    private MenusMapper menusMapper;

    private RoleCache roleCache;

    @Autowired
    public void setRoleCache(RoleCache roleCache) {
        this.roleCache = roleCache;
    }

    @Autowired
    public void setMenusMapper(MenusMapper menusMapper) {
        this.menusMapper = menusMapper;
    }

    @Override
    public List<Menu> findAllMenus() {
        return menusMapper.findAllMenus();
    }

    @Override
    public List<Menu> findMenuByRoleId(Long rid) {
        return menusMapper.findMenuByRoleId(rid);
    }

    @Override
    public Set<Menu> findAllLeftMenus() {
        //经过测试,我们发现直接返回所有的数据会出错,因为不是所有的用户都有权限访问这些地址
        //应该返回的是当前用户有的东西
        //获取到当前登陆的用户的授权信息
        BaseUser baseUser = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取内部保存的所有的角色
        List<Role> roleList = baseUser.getRoleList();//获取当前登陆用户的所有的角色
        List<Role> allData = roleCache.getAllData();
        System.err.println("alldata"+allData);
        Set<Menu> userMenus = new HashSet<>();//保存当前用户左侧菜单的集合
        //遍历用户的角色,找到用户在左侧显示的数据,也就是类型为 1 的数据
        roleList.forEach(role -> {
            userMenus.addAll(role.getMenuList().stream().filter(menu -> {
                return menu.getType() == 1L;
            }).collect(Collectors.toSet()));
        });
        return userMenus;
    }

    @Override
    public void addMenu(Menu menu) {
        if (menu.isNull(CheckStatus.ADD)) {
            throw new AddErrorException("有必填项没有填写", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        menusMapper.addMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        if (menu.isNull(CheckStatus.UPDATE)){
            throw new UpdateErrorException("没有更改内容，更新失败",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        menusMapper.updateMenu(menu);
    }


}
