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
        http.csrf().disable();//????????????????????????csrf ????????? //????????????????????????????????????post?????????????????????
        allMenus.forEach(menu -> {
            try {
                if (!ObjectUtils.isEmpty(menu.getUrl())){
                        //?????????????????????????????????
                        http.authorizeRequests().antMatchers(menu.getUrl()).hasAuthority(menu.getPerms());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        http.authorizeRequests()
//                .mvcMatchers("/login").permitAll()
                .and().formLogin()
//                .loginPage()//???????????????????????????
                .loginProcessingUrl("/login")//??????????????????????????????
                .successHandler(mySuccessHandler)
                .and().authorizeRequests().anyRequest().authenticated();//??????????????????????????????????????????????????????????????????

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/js/**",
                "/layui/**",
                "/websocket/**",
                "/colorcommand/**", "/humiture/**"
        );//?????????????????? mvcMatchers("/login").permitAll() ???????????????????????????????????????????????????
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
