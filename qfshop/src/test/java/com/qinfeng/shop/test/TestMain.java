package com.qinfeng.shop.test;


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


import com.qianfeng.shop.pojo.TbUser;
import com.qianfeng.shop.utils.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackiechan on 2021/9/13 11:06
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */

public class TestMain {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = new HashMap<>();
        map.put("username", new String[]{"张三","dasdasa"});
        map.put("password", new String[]{"12321312"});
//        map.put("email", "dadasd@dasdas.com");
//        map.put("gender", "男");
//        map.put("flag", "0");
        map.put("role",new String[]{ "1"});
//        map.put("hobbies",new String[]{ "1","2"});
//        map.put("code", "dasdas");
        TbUser user = new TbUser();
        BeanUtils.populate(user,map);
        System.err.println(user);
    }

    // 返回值 void 参数没有
    @Test
    public void testMD5() {
        System.err.println(MD5Utils.md5("123456"));
    }
}
