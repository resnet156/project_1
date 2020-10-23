package com.m9day6;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName TCPTest1
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/6 18:10
 * @Version 1.0
 **/
public class TCPTest1 {
    @Test
    public void cilent() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1，创建Socket对象，指明服务器端的IP和端口号
            InetAddress inet1 = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet1, 8899);
            //2，获取一个输出流，用于获取数据
            os = socket.getOutputStream();
            //3,写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4,关闭资源
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1,创建服务器端的ServerSocket,指明自己的端口号
            ss = new ServerSocket(8899);
            //2,调用accept(),表示接受来自于客户端的socket
            socket = ss.accept();
            //3,获取一个输入流
            is = socket.getInputStream();
        /*byte[] buffer = new byte[8];
        int len;
        while ((len = is.read(buffer)) != -1){
            String s = new String(buffer, 0, len);
            System.out.println(s);
        }*/
            //4,读入输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer, 0 , len);
            }
            System.out.println(baos.toString());

            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5,关闭资源
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
