package com.qianfeng.smartdevices.security;

import com.qianfeng.smartdevices.pojo.BaseUser;
import com.qianfeng.smartdevices.pojo.Menu;
import com.qianfeng.smartdevices.pojo.Role;
import com.qianfeng.smartdevices.service.MenusService;
import com.qianfeng.smartdevices.service.RoleService;
import com.qianfeng.smartdevices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private RoleService roleService;

    private MenusService menusService;




    @Autowired
    public void setMenusService(MenusService menusService) {
        this.menusService = menusService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //这是登陆之后要干的事情写到这
        System.err.println("登陆成功");
        //获取到当前用户的信息
        BaseUser baseUser = (BaseUser) authentication.getPrincipal();
        System.err.println(baseUser);
        System.err.println(authentication.getCredentials());
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();//用于保存用户所有权限的集合

        Long uid = baseUser.getUid();//拿到当前用户的id
        //根据用户id查询用户的角色，然后查询菜单
        List<Role> roles = roleService.findRoleByUserId(uid);
        roles.forEach(role -> {//遍历角色，查询每个角色的菜单，并设置进去
            List<Menu> menus = menusService.findMenuByRoleId(role.getId());
            role.setMenuList(menus);
            //将查询到的角色和权限设置给当前登陆的用户
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));//给用户添加角色
            //设置权限
            //将权限设置到用户身上
            grantedAuthorities.addAll(menus.stream().map(Menu::getPerms).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        });
        baseUser.setRoleList(roles);//保存了用户所有的角色,方便在其他地方使用
        //刷新权限，重新给用户授权
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(baseUser,authentication.getCredentials(),grantedAuthorities));
        response.sendRedirect("/index.html");
    }
}
