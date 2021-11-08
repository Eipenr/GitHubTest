package com.qianfeng.smartdevices.config;

import com.qianfeng.smartdevices.mapper.MenusMapper;
import com.qianfeng.smartdevices.pojo.Menu;
import com.qianfeng.smartdevices.security.MySuccessHandler;
import com.qianfeng.smartdevices.service.MenusService;
import com.qianfeng.smartdevices.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

import java.awt.print.PrinterGraphics;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {


    private MenusService menusService;

    private MySuccessHandler mySuccessHandler;

    private MyUserDetailsService myUserDetailsService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setMySuccessHandler(MySuccessHandler mySuccessHandler) {
        this.mySuccessHandler = mySuccessHandler;
    }

    @Autowired
    public void setMenusService(MenusService menusService) {
        this.menusService = menusService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<Menu> allMenus = menusService.findAllMenus();
        http.csrf().disable();//禁用掉，不然需要csrf 头信息 //设置禁止跨域请求，要不然post请求会进行拦截
        allMenus.forEach(menu -> {
            try {
                if (!ObjectUtils.isEmpty(menu.getUrl())){
                        //设置每个地址需要的权限
                        http.authorizeRequests().antMatchers(menu.getUrl()).hasAuthority(menu.getPerms());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        http.authorizeRequests()
//                .mvcMatchers("/login").permitAll()
                .and().formLogin()
//                .loginPage()//使用自己的登陆界面
                .loginProcessingUrl("/login")//自定义登陆的接口地址
                .successHandler(mySuccessHandler)
                .and().authorizeRequests().anyRequest().authenticated();//除了上面请求的地址之外，其他的地址都需要认证

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/js/**",
                "/layui/**",
                "/websocket/**",
                "/colorcommand/**", "/humiture/**"
        );//这个和上面的 mvcMatchers("/login").permitAll() 方法是一样的，就是设置不过滤的地址
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置如何从数据库中根据用户名查询数据并返回结果，并设置数据库中的密码是通过什么方式生成的
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
