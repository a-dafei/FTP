package com.lpf.www.ftp.server;



import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.command.CommanFactory;
import com.lpf.www.ftp.entity.UserInfo;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"GBK"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));

			// Ϊ����command������Ϣ
			UserInfo userInfo = new UserInfo();
			userInfo.setSocket(socket);
			// ��һ�ν�������
			writer.write("Connection succeeded...\r\n");
			writer.flush();
			reader.readLine();
			writer.write("\nPlease enter your user name: \r\n");
			writer.flush();

			// ���ܿͻ��˷�������Ϣ
			while (true) {

				if (!socket.isClosed()) {
					String result = null;
					try {
						result = reader.readLine();
					} catch (Exception e) {
						System.out.println("The client has forced the server to shut down! \r\n ");
					}
					System.out.println(result);// ��ӡ���ͻ��˷��͵�����
					if (result != null && result != "") {
						String[] datas = result.split(" ");
						BaseCommand command = CommanFactory.parseCommand(datas[0]);
						if (command != null) {
							// ���ͻ��˷���������ֻ������û�к��������ʱ��
							if (datas.length == 1) {
								command.executeCommand("", writer, userInfo);
							} else {
								command.executeCommand(datas[1], writer, userInfo);
							}
						} else {
							writer.write("No such command, please re-enter: \r\n ");
							writer.flush();
						}


					} else {
						// ��ʱ�ͻ���ǿ�йر�������
						reader.close();
						writer.close();
						socket.close();
						break;
					}

				} else {

					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
