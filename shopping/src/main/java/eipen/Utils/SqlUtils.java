package eipen.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlUtils {
    private static Properties properties=new Properties();

    public static String getSql(String sql){
        try {
            InputStream inputStream = SqlUtils.class.getResourceAsStream("/sql.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(sql);
    }
}
