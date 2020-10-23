package com.m9day6;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName UDPTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/6 20:50
 * @Version 1.0
 **/
public class UDPTest {
    //发送端
    @Test
    public void sender() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            String s = new String("通过UDP方式发射的导弹");
            byte[] data = s.getBytes();
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet, 9090);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                socket.close();
            }
        }

    }
    //接收端
    @Test
    public void recevier(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9090);
            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, 0, data.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                socket.close();
            }
        }

    }
}
