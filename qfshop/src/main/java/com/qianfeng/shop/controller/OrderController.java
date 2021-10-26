package com.qianfeng.shop.controller;


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


import com.qianfeng.shop.annotations.RequestMappping;
import com.qianfeng.shop.pojo.CartItem;
import com.qianfeng.shop.pojo.Order;
import com.qianfeng.shop.pojo.TbAddress;
import com.qianfeng.shop.pojo.TbUser;
import com.qianfeng.shop.service.AddressService;
import com.qianfeng.shop.service.CartService;
import com.qianfeng.shop.service.OderService;
import com.qianfeng.shop.service.impl.AddressServiceImpl;
import com.qianfeng.shop.service.impl.CartServiceImpl;
import com.qianfeng.shop.service.impl.OderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jackiechan on 2021/9/18 09:46
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */

@RequestMappping("/order")
public class OrderController {

    private CartService cartService = new CartServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    private OderService oderService = new OderServiceImpl();

    @RequestMappping("/preView")
    public String preView(HttpServletRequest request, HttpServletResponse response) {
        TbUser loginUser = (TbUser) request.getSession().getAttribute("loginUser");
        Long uid = loginUser.getId();
        if (request.getSession().getAttribute("cart") == null) {
            List<CartItem> cartItemList = cartService.findCartByUid(uid);//获取到购物车数据
            request.getSession().setAttribute("cart", cartItemList);
        }

        if (request.getSession().getAttribute("addressList") == null) {
            //获取购物车数据
            //获取地址数据
            List<TbAddress> addressList = addressService.findAllAddresByUid(uid);
            //携带数据
            //跳转到预览页面
            request.getSession().setAttribute("addressList", addressList);
        }

        return "forward:/order.jsp";

    }

    @RequestMappping(("/create"))
    public String createOrder(HttpServletRequest request, HttpServletResponse response) {
        //创建订单需要知道购买的商品和数量,我们直接把购物车里面所有的数据都下单
        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");//先获取到要下单的商品
        //还得知道地址选择的是哪个, 地址从前端传过来
        String aid = request.getParameter("aid");//用户选择的地址
        Order order = oderService.createOrder(cart, aid);
        request.getSession().setAttribute("order",order);
        //携带数据 数据在哪?
        return "redirect:/orderSuccess.jsp";
    }

}
