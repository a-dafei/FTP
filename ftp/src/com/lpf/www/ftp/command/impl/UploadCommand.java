package com.lpf.www.ftp.command.impl;



import com.lpf.www.ftp.command.BaseCommand;
import com.lpf.www.ftp.entity.UserInfo;
import com.lpf.www.ftp.utils.AccountUtil;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
/**
 * 文件上传
 */
public class UploadCommand implements BaseCommand {

    /**
     * 文件上传
     * @param datas
     * @param writer
     * @param userInfo
     */
    @Override
    public void executeCommand(String datas, BufferedWriter writer, UserInfo userInfo) {
        File file = new File(AccountUtil.getRootPath());
        try{
            writer.write("\n  Binary data connection\r\n");
            writer.write("\n  Upload succeeded...\r\n");
            writer.flush();
            File lastFileName = new File(AccountUtil.getRootPath()+"/"+datas);
            String name = AccountUtil.getRootPath()+datas;
            for (String item:file.list()){
                item = AccountUtil.getRootPath()+item;
                //获取相同文件名
                if (item.equals(name)){
                    //将文件名改成时间戳
                    File temp = new File(AccountUtil.getRootPath()+ Calendar.getInstance().getTimeInMillis()+"."+item.substring(item.lastIndexOf(".")+1));
                    lastFileName.renameTo(temp);
                    System.out.println(lastFileName);

                }
            }
            RandomAccessFile inFile = new RandomAccessFile(lastFileName,"rw");
            // 开始上传
            Socket tempSocket = new Socket("127.0.0.1", 30);
            InputStream inSocket = tempSocket.getInputStream();
            byte byteBuffer[] = new byte[1024];
            int length;

            while((length =inSocket.read(byteBuffer) )!= -1){
                inFile.write(byteBuffer, 0, length);
            }
            System.out.println("transfer complete");
            inFile.close();
            inSocket.close();
            tempSocket.close();
            //传输结束
            /**
            writer.write("\nUpload succeeded...\r\n");
            writer.flush();
             */

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
