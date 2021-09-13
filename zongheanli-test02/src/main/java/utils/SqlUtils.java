package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlUtils {
    private static Properties properties=new Properties();
    public static String getSql(String sql) throws IOException {
        InputStream inputStream = SqlUtils.class.getResourceAsStream("/sql.properties");
        properties.load(inputStream);
        return properties.getProperty(sql);
    }
}
