package eipen.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String password){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            //BigInteger 是将8位的字符串转换成16位的字符串  得到的字符串是哈希码值
            //BigInteger （参数1，参数2） 参数1 是 1为正数 0为0 -1为负数
            return new BigInteger(1,md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
