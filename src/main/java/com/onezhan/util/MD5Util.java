package com.onezhan.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
    public static String md5(String input) {
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(input.getBytes("UTF-8"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
}
