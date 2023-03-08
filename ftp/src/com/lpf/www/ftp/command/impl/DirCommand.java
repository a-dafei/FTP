package com.lpf.www.ftp.command.impl;

import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;
import com.lpf.www.ftp.utils.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * 文件列表
 */
public class DirCommand implements BaseCommand {


	/**
	 * dir功能实现
	 * @param datas
	 * @param writer
	 * @param userInfo
	 */
	@Override
	public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo) {
		File file = new File(AccountUtil.getRootPath());
		if (!file.isDirectory()) {
			try {
				writer.write("\n The file does not exist!\r\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//拼接文件目录字符串
			StringBuffer dirList = new StringBuffer();
			int count = 1;
			for(String item:file.list()){
				File itemFile = new File(file+File.separator+item);
				String size = FileUtil.getFileSize(itemFile);
				if (size.equals("")||size==null) {
					size = "dir";
				}else{
					size += "	file";
				}
				dirList.append(count+"	"+item+"	"+size);
				dirList.append("\r\n");
				count++;
			}
			try {
				writer.write("\n  open ascii mode...\r\n");
				writer.flush();
				writer.write(dirList + "\r\n");
				writer.write(" transfer complete...\r\n");
				writer.flush();
				System.out.println(dirList.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
