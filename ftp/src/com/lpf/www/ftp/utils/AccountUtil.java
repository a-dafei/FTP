package com.lpf.www.ftp.utils;


import java.io.File;
import java.util.HashMap;

public class AccountUtil {
	

	static String fileName = "server.xml";
	static String rootDir = "F:\\�����̼���\\���������"+File.separator;//������λ�ã����ڴ洢�ļ�
	static HashMap<String, String> users = new HashMap<String, String>();

	public static void initAccount() {
		File file = new File(System.getProperty("user.dir")+"/bin/"+fileName);
        // ԭ�������Ϊ123456���������123456��md5���ܺ���ַ���
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
