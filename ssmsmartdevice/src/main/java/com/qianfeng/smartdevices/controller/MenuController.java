package com.qianfeng.smartdevices.controller;


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


import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.Menu;
import com.qianfeng.smartdevices.pojo.Menus;
import com.qianfeng.smartdevices.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jackiechan on 2021/10/11 14:38
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/menus")
public class MenuController {

    private MenusService menusService;

    @Autowired
    public void setMenusService(MenusService menusService) {
        this.menusService = menusService;
    }

    @GetMapping("/menus")
    @AopLogAnnotation("查找所有菜单列表")
    public R findAllMenus() {
        Set<Menu> menusList = menusService.findAllLeftMenus();//获取到所有的菜单
        return R.setOK(menusList);
    }

    @PostMapping("/menu")
    @AopLogAnnotation("添加菜单列表")
    public R addMenu(@RequestBody Menu menu){
        menusService.addMenu(menu);
        return R.setOK();
    }

    @PutMapping ("/menu")
    @AopLogAnnotation("更新菜单列表")
    public R updateMenu(@RequestBody Menu menu){
        menusService.updateMenu(menu);
        return R.setOK();
    }




}
