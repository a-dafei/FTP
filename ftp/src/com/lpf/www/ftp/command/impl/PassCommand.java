package com.lpf.www.ftp.command.impl;

import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;
import com.lpf.www.ftp.utils.Md5Util;

import java.io.BufferedWriter;
import java.io.IOException;


/**
 * 密码验证
 */
public class PassCommand implements BaseCommand {

	/**
	 * 密码验证
	 * @param passWord
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String passWord, BufferedWriter writer, UserInfo userInfo) {
 		String passWord2Md5 = Md5Util.encoder(passWord);
		if (passWord2Md5.equals(AccountUtil.getPassword(userInfo.getUsername()))) {
			System.out.println("密码正确"+passWord + ",md5密码："+passWord2Md5);
			try {
				writer.write("\n The password is correct!Welcome !\r\n");
				writer.flush();
				userInfo.setPassword(passWord2Md5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				writer.write("\n Password error!Please re-enter your password:\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
