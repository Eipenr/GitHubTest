package dao.impl;

import dao.StudentDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Student;
import utils.DbUtils;
import utils.SqlUtils;

import java.io.IOException;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private QueryRunner queryRunner=new QueryRunner(DbUtils.getDataSource());
    @Override
    public long addStudent(Student student) {
        try {
            long i = queryRunner.insert(SqlUtils.getSql("student.add"), new ScalarHandler<>(), student.getStuName(), student.getStuGender(), student.getStuPhone(), student.getBornDate(), student.getStuEmail(), student.getStuAddress(), student.getGradeId());
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
