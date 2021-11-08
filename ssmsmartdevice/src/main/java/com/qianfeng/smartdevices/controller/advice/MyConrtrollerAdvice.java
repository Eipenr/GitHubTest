package com.qianfeng.smartdevices.controller.advice;


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


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.event.LogsEvent;
import com.qianfeng.smartdevices.exceptions.MybaseException;
import com.qianfeng.smartdevices.pojo.BaseUser;
import com.qianfeng.smartdevices.pojo.LogData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by jackiechan on 2021/10/11 15:18
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
@ResponseBody
@Aspect //切面类
public class MyConrtrollerAdvice {


    private ObjectMapper objectMapper;

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(MybaseException.class)
    public R processMyBaseException(MybaseException exception) {
        String message = exception.getMessage();
        int code = exception.getCode();
        return R.SetError(null, message, code);
    }

    /**
     * 处理未知异常的
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R processMyBaseException(Exception exception) {
        exception.printStackTrace();
        return R.SetError(null, "请检查网络", ResultCode.ERROR);
    }

    @Pointcut("execution(* com.qianfeng.smartdevices.controller.*.*(..))")
    public void qierudian(){

    }

    @Around("qierudian()")
    public Object processAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            LogData logData = new LogData();
            HashMap<Object, Object> map = new HashMap<>();

            //得到的是参数的值
            Object[] args = joinPoint.getArgs();
            //还要想办法获取参数的名字
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();//拿到的是方法的全限定名
            String[] parameterNames = methodSignature.getParameterNames();
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    String parameterName = parameterNames[i];//获取到每一个参数的名字
                    Object arg = args[i];//这是每个参数的值
                    map.put(parameterName,arg);
                }
            }
            String parmaJson = objectMapper.writeValueAsString(map);
            logData.setParmas(parmaJson);
//            System.err.println("22222222222"+parmaJson);

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal!=null){
                String username = ((BaseUser) principal).getUsername();//拿到当前用户名
                logData.setUser(username);
            }
            Object proceed = joinPoint.proceed();//执行原始的操作
            if (proceed instanceof R){
                Object result = ((R) proceed).getResult();//真正的结果
                logData.setResult(objectMapper.writeValueAsString(result));
                logData.setMsg(((R) proceed).getMsg());
                logData.setCode(((R) proceed).getCode());
            }

            //我们已经知道了当前是谁在操作，获取到了参数和返回结果，就差获取到正在干什么
            //通过向方法上添加一个注解@AopLogAnnotation 来说明我们实在干什么
            Method method = methodSignature.getMethod();//获取到当前处理请求的方法名称
            AopLogAnnotation annotation = method.getAnnotation(AopLogAnnotation.class);
            if (annotation != null) {
                String caozuo = annotation.value();//注解中的value 也就是我们备注的正在做什么
                logData.setCaozuo(caozuo);
            }
//            System.err.println("这是收集到的数据"+logData);
            context.publishEvent(new LogsEvent(logData));
            return proceed;

        } catch (Throwable e) {
            e.printStackTrace();
            throw e;

        }

    }


}
