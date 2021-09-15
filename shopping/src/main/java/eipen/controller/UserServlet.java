package eipen.controller;

import com.alibaba.druid.util.StringUtils;
import eipen.Exceptions.MybaseException;
import eipen.pojo.TbUser;
import eipen.service.UserService;
import eipen.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "register":
                register(req,resp);
                break;
            case "login":
                login(req,resp);
                break;
            case "logOut":
                logOut(req,resp);
                break;
        }
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //获取前端传过来的用户名密码
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //交个Service进行判空
            TbUser user = userService.login(username, password);
            if(user==null){
                resp.getWriter().write("用户名或密码错误");
            }
            //不为空就将user保存到Session 中
            req.getSession().setAttribute("loginUser",user);
            resp.sendRedirect("/index.jsp");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            try {
                resp.sendRedirect("/error/error.jsp");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            //注册需要把前端的数据拿过来，利用BeanUtils 这个工具包
            //把前端的参数以集合的形式取出来
            Map<String, String[]> parameterMap = req.getParameterMap();
            TbUser user=new TbUser();
            BeanUtils.populate(user,parameterMap);//将信息按照键值对的形式赋值到user对象中
            //为了减轻一下Service 的负担，在这里先判断一下用户输的两次密码是否一致
            //拿到第二次输入的密码
            String repeatpassword = req.getParameter("repeatpassword");
            if(StringUtils.isEmpty(repeatpassword)||!repeatpassword.equals(user.getPassword())){
                resp.getWriter().write("两次输入密码不一致");
                return;
            }
            //进行一个空的判断
            if(user.isNull()){
                resp.getWriter().write("有必填的信息没有进行填写");
                return;
            }
            //如果两次输入的密码都写了并且一致，就交给Service 层进行检查
            userService.addUser(user);
            resp.sendRedirect("/registerSuccess.jsp");


        } catch (MybaseException e) {
            e.printStackTrace();
            req.getSession().setAttribute("msg",e.getMessage());
            resp.sendRedirect("/error/fail.jsp");
        }  catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/error/error.jsp");
        }


    }
}
