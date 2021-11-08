package com.qianfeng.smartdevices.dto;


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


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by jackiechan on 2020/12/7 19:33
 *
 * @Author jackiechan
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class R {
    private String msg;
    private int code;
    private Object result;


    public static R setOK() {
        return setOK(null);
    }

    public static R setOK(Object data) {
        R result = new R();
        result.setMsg("success");
        result.setCode(ResultCode.SUCCESS);
        result.setResult(data);
        return result;
    }

    public static R SetError(Object data) {
        return SetError(data, "fail", ResultCode.ERROR);
    }

    public static R SetError(Object data, String msg, int code) {
        R result = new R();
        result.setMsg(msg);
        result.setCode(code);
        result.setResult(data);
        return result;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
