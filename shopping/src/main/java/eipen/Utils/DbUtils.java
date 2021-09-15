package eipen.Utils;


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 连接数据库
 */
public class DbUtils {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    static {
        try {
            InputStream inputStream = DbUtils.class.getResourceAsStream("/db.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            dataSource=new DruidDataSource();
            ((DruidDataSource) dataSource).setPassword(properties.getProperty("password"));
            ((DruidDataSource) dataSource).setUsername(properties.getProperty("username"));
            ((DruidDataSource) dataSource).setDriverClassName(properties.getProperty("driver"));
            ((DruidDataSource) dataSource).setUrl(properties.getProperty("url"));
            ((DruidDataSource) dataSource).setMaxActive(50);
            ((DruidDataSource) dataSource).setMaxIdle(20);
            ((DruidDataSource) dataSource).setInitialSize(10);
            ((DruidDataSource) dataSource).setMinIdle(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
