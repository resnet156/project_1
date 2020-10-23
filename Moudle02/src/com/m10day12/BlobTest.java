package com.m10day12;

import com.m10day8.Customer;
import com.m10day8.JDBCUtils;
import org.testng.annotations.Test;

import java.io.*;
import java.sql.*;

/**
 * @ClassName BlobTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/14 22:45
 * @Version 1.0
 **/
public class BlobTest {

    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(`name`, email, birth, photo) values (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, "郭庚龙");
        ps.setObject(2, "guo@qq.com");
        ps.setObject(3, "2000-04-13");

        FileInputStream is = new FileInputStream(new File("vr.jpg"));
        ps.setBlob(4, is);

        ps.execute();
        JDBCUtils.closeResource(conn, ps);
    }

    @Test
    public void testQuery(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, `name`, email, birth, photo from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 16);
            rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birth = rs.getDate("birth");

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
                //将Blob类型的字段下载下来，以文件的形式保存下来
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("zhangyuhao.jpg");

                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(conn, ps, rs);
        }

    }
}
