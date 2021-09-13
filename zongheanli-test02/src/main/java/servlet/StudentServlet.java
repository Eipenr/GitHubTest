package servlet;

import pojo.Student;
import service.StudentService;
import service.impl.addStudentImpl;
import utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/studentOperator")
public class StudentServlet extends HttpServlet {
    private StudentService studentService=new addStudentImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        switch (option){
            case "add":
                addStudent(req,resp);
                break;
            case "delete":

                break;
            case "update":

                break;
            case "updatePage":

                break;
            case "queryList":

                break;
        }



    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Student student = BeanUtil.processParameters(req, Student.class);
            long i = studentService.addStudent(student);
            if(i>0){
                resp.getWriter().write("添加成功喽");
            }else {
                resp.getWriter().write("添加失败了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
