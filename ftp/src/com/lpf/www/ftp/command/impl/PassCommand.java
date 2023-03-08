package com.lpf.www.ftp.command.impl;

import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;
import com.lpf.www.ftp.utils.Md5Util;

import java.io.BufferedWriter;
import java.io.IOException;


/**
 * ������֤
 */
public class PassCommand implements BaseCommand {

	/**
	 * ������֤
	 * @param passWord
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String passWord, BufferedWriter writer, UserInfo userInfo) {
 		String passWord2Md5 = Md5Util.encoder(passWord);
		if (passWord2Md5.equals(AccountUtil.getPassword(userInfo.getUsername()))) {
			System.out.println("������ȷ"+passWord + ",md5���룺"+passWord2Md5);
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
