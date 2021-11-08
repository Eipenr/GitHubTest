package com.eipen.springcloud.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error",produces = "text/html;charset=utf-8")
    public String processError(){
        return "大哥你出错了，---------------";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
