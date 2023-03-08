package com.lpf.www.ftp.command;



import com.lpf.www.ftp.entity.UserInfo;

import java.io.BufferedWriter;

public interface BaseCommand {

	public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo);
}
