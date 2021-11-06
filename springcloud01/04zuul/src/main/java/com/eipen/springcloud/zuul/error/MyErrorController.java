package com.eipen.springcloud.zuul.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error",produces = "text/html;charset=utf-8")
    public String processError(){
        return "欧巴 撒拉黑";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
