package com.lpf.www.ftp.server;

import com.lpf.www.ftp.utils.Md5Util;


public class TestMD5 {
    public static void main(String[] args) {
        String passWord = "123456";
        String passWord2Md5 = Md5Util.encoder(passWord);
        System.out.println(passWord2Md5);
    }
}
