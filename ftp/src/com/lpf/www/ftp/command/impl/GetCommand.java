package com.lpf.www.ftp.command.impl;

import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;


/**
 * 文件下载
 */
public class GetCommand implements BaseCommand {


	@Override
	public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo) {
		File file = new File(AccountUtil.getRootPath()+File.separator+datas);
		File rootFile = new File(System.getProperty("user.home")+File.separator);
		File lastFileName = new File(System.getProperty("user.home")+File.separator+datas);
		String name = System.getProperty("user.home")+"\\"+datas;
		if (file.exists()) {
			// 检测是否有相同的文件名
			for (String item:rootFile.list()){
				item = System.getProperty("user.home")+"\\"+item;
				// 获取相同文件名
				if (item.equals(name)){
					// 如果有则改掉将文件名改成时间戳
					File temp = new File(System.getProperty("user.home")+"\\"+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
					lastFileName.renameTo(temp);

				}
			}
			try {
				writer.write("\n open ascii mode...\r\n");
				writer.flush();

				Socket socket = new Socket("127.0.0.1", 30);
				OutputStream outputStream = socket.getOutputStream();
				FileInputStream inputStream = new FileInputStream(file);
				int length = 0;
				byte[] buff = new byte[1024];
				while((length = inputStream.read(buff))!=-1){
					outputStream.write(buff, 0, length);
				}
				// 传输结束
				inputStream.close();
				outputStream.close();
				socket.close();

				writer.write("\n Download succeeded...\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else{
			try {
				writer.write("\n The file does not exist!\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
