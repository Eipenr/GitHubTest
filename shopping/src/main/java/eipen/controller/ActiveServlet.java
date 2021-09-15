package eipen.controller;

import com.alibaba.druid.util.StringUtils;
import eipen.Utils.EmailUtil;
import eipen.service.UserService;
import eipen.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 激活验证的一个Servlet
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要发送邮件，通过用户点击邮件中的链接进行激活验证
        //创建一个EmailUtil工具类
        String username = req.getParameter("username");
        String code = req.getParameter("code");
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(code)){
            resp.getWriter().write("玩呢，参数哪去了");
            return;
        }
        //如果用户正常传递了数据过来，则代表已经激活了，然后进入数据库将它的激活状态改为1
        try {
            int result = userService.active(username, code);
            if(result==1){
                resp.getWriter().write("恭喜你，激活成功");
            }else {
                resp.getWriter().write("很遗憾，激活失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("/error/error.jsp");
        }


    }
}
