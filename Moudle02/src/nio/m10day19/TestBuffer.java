package nio.m10day19;

import org.junit.Test;

import java.nio.ByteBuffer;


/*
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 *
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */
/**
 * @ClassName TestBuffer
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/19 17:19
 * @Version 1.0
 **/
public class TestBuffer {
    //分配直接缓冲区
    @Test
    public void test3(){
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        //判断是否是直接缓冲区
        System.out.println(buf.isDirect());
    }
    @Test
    public void test2(){
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, dst.length));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //mark()
        buf.mark();
        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        //reset() 回到mark()标记的位置
        buf.reset();
        System.out.println(buf.position());
        //判断缓冲区内是否还有剩余数据
        if (buf.hasRemaining()){
            //获取缓冲区内可操作的数量
            System.out.println(buf.remaining());
        }

    }
    @Test
    public void test1(){
        //1,分配一个指定大小的缓冲区
        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("-------------------allocate()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2,利用put() 存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("-------------------put()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3,切换到读取数据的模式
        buf.flip();

        System.out.println("-------------------flip()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4,利用get()  读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("-------------------get()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5,rewind()  可重复读数据
        buf.rewind();

        System.out.println("-------------------rewind()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6,clear()  清空缓冲区 但缓冲区的数据依然存在，但是处于“被遗忘的状态”
        buf.clear();

        System.out.println("-------------------clear()-------------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
    }
}
