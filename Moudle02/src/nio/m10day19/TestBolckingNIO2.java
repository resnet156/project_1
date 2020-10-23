package nio.m10day19;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */

/**
 * @ClassName TestBolckingNIO2
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/20 21:20
 * @Version 1.0
 **/
public class TestBolckingNIO2 {
    //客户端
    @Test
    public void client() {
        SocketChannel sChannel = null;
        FileChannel inChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inChannel.read(buf) != -1){
                buf.flip();
                sChannel.write(buf);
                buf.clear();
            }
            sChannel.shutdownOutput();
            //接受服务端的反馈
            int len = 0;
            while ((len = sChannel.read(buf)) != -1){
                buf.flip();
                System.out.println(new String(buf.array(), 0, len));
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sChannel != null){
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //服务端
    @Test
    public void server() {
        ServerSocketChannel ssChannel = null;
        FileChannel outChannel = null;
        SocketChannel sChannel = null;
        try {
            ssChannel = ServerSocketChannel.open();
            outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ssChannel.bind(new InetSocketAddress(9898));
            sChannel = ssChannel.accept();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (sChannel.read(buf) != -1){
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
            //发送反馈给服务端
            buf.put("服务端接受数据成功".getBytes());
            buf.flip();
            sChannel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sChannel != null){
                try {
                    sChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ssChannel != null){
                try {
                    ssChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
