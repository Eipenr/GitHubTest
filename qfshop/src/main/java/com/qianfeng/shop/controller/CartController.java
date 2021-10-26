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
import com.qianfeng.shop.pojo.TbUser;
import com.qianfeng.shop.service.CartService;
import com.qianfeng.shop.service.impl.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jackiechan on 2021/9/17 09:59
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@RequestMappping(("/cart"))
public class CartController {
    private CartService cartService = new CartServiceImpl();

    @RequestMappping("/create")
    public String addCart(HttpServletRequest request, HttpServletResponse response) {
        String goodId = request.getParameter("id");//获取用户想要添加到购物车的商品的 id
        //获取到当前的登录用户
        TbUser loginUser = (TbUser) request.getSession().getAttribute("loginUser");
        Long uid = loginUser.getId();//登陆用户的 id
        int num = 1;//现在固定数量 1,我们没有设置数量的操作,如果有了,只需要从参数中获取就可以
        try {
            cartService.addCart(goodId,uid,num);
            response.setContentType("text/html;charset=utf-8");
            return "redirect:/cartSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/error.jsp";
        }
    }
    @RequestMappping("/show")
    public String show(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("cart") == null) {//如果没有查询过购物车,就先查询,再保存
            //获取到当前登录的用户,因为用户只能看自己的购物车
            TbUser loginUser = (TbUser) request.getSession().getAttribute("loginUser");
            Long uid = loginUser.getId();//登陆用户的 id
            List<CartItem> cartByUid = cartService.findCartByUid(uid);//获取到当前用户的购物车数据
            request.getSession().setAttribute("cart", cartByUid);
        }
        return "forward:/cart.jsp";
    }
}
