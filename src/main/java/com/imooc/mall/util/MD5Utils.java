package com.imooc.mall.util;

import com.imooc.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：加密工具
 */
public class MD5Utils {

    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getMD5Str("1234"));
    }
}
