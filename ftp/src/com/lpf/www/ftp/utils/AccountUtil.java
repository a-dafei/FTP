package com.lpf.www.ftp.utils;


import java.io.File;
import java.util.HashMap;

public class AccountUtil {
	

	static String fileName = "server.xml";
	static String rootDir = "F:\\网络编程技术\\网编服务器"+File.separator;//服务器位置，用于存储文件
	static HashMap<String, String> users = new HashMap<String, String>();

	public static void initAccount() {
		File file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
        // 原生密码均为123456，下面的是123456的md5加密后的字符串
        users.put("lpf", "e10adc3949ba59abbe56e057f20f883e");
        users.put("lipengfei", "e10adc3949ba59abbe56e057f20f883e");


	}
	
	public static String getRootPath() {
		return rootDir;
	}
	
	public static boolean hasUsername(String username) {
		if (users.get(username)!=null) {
			return true;
		}else{
			return false;
		}
	}

	public static String getPassword(String userName) {
		return users.get(userName);
	}
}
