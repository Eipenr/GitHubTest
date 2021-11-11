package com.qianfeng.redis;


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


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Created by jackiechan on 2021/10/28 15:36
 *
 * @author jackiechan
 * @version 1.0
 * //TODO 给最美丽的芳姐介绍学生
 * @since 1.0
 */

public class TestMain {

    @Test
    public void test1() {
        Jedis jedis=new Jedis("192.168.3.13",6379);
        jedis.set("jedis","zenmezhemka");
        System.err.println(jedis.get("name"));
        jedis.close();
    }



    @Test
    public void test2() {
        Jedis jedis=new Jedis("192.168.3.13",6379);
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set("key" + i, i + "");
        }
        long timeMillis = System.currentTimeMillis();
        System.err.println(timeMillis - currentTimeMillis);
        jedis.close();
    }


    @Test
    public void test3() {
        Jedis jedis=new Jedis("192.168.3.13",6379);
        long currentTimeMillis = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("key" + i, i + "");
        }
        pipeline.syncAndReturnAll();
        long timeMillis = System.currentTimeMillis();
        System.err.println(timeMillis - currentTimeMillis);
        jedis.close();
    }
}
