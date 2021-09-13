package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import pojo.Student;
import service.StudentService;

import java.util.regex.Pattern;

public class addStudentImpl implements StudentService {
    private StudentDao studentDao=new StudentDaoImpl();
    @Override
    public long addStudent(Student student) {
        if(student.isNull()){
            System.err.println("有未填写的内容，要反馈给前端");
            return 0;
        }

        String pattern="(13|14|15|18)[0-9]{9}";
        boolean matches = Pattern.matches(pattern, student.getStuPhone());
        if(matches){
            return studentDao.addStudent(student);
        }else {
            System.err.println("手机号格式不正确");
        }
        return 0;
    }
}
