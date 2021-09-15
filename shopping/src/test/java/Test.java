import eipen.Utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class Test {
    public static void main(String[] args) {
        DataSource dataSource = DbUtils.getDataSource();
        System.err.println(dataSource);
    }

    @org.junit.Test
    public  void Test1(){
        QueryRunner queryRunner=new QueryRunner(DbUtils.getDataSource());
        System.err.println(queryRunner);
    }
}

