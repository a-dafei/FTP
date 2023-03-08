package com.lpf.www.ftp.command.impl;

import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;

import java.io.*;

/**
 * 用户验证
 */
public class UserCommmand implements BaseCommand {

	private BufferedReader reader;

	@Override
	public void executeCommand(String userName, BufferedWriter writer, UserInfo userInfo) {
		if (AccountUtil.hasUsername(userName)) {
			System.out.println("user:"+userName+" access has been granted!");
			try {
				writer.write("\nPlease enter your password:\r\n");
				writer.flush();
				userInfo.setUsername(userName);
				//reader.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("user:"+userName+"has no authority!");
			try {
				writer.write("This user does not have permission!\r\n\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
