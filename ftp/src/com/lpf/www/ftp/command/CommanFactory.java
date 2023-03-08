package com.lpf.www.ftp.command;


import com.lpf.www.ftp.command.impl.*;

public class CommanFactory {

	public static BaseCommand parseCommand(String name) {
		BaseCommand command  = null;
		switch(name){
		case "user" :
			command = new UserCommmand();
			break;
		case "pass" :
			command = new PassCommand();
			break;
		case "get" :
			command = new GetCommand();
			break;
		case "dir" :
			command = new DirCommand();
			break;
		case "upload" :
            command = new UploadCommand();
            break;
		}
		return command;		
	}
}
