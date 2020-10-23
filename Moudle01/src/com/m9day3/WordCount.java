package com.m9day3;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName WordCount
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/9/4 17:41
 * @Version 1.0
 **/
public class WordCount {
    @Test
    public void test1(){
        BufferedReader brf = null;
        BufferedWriter bwf = null;
        try {
            brf = new BufferedReader(new FileReader("dbcp.txt"));
            bwf = new BufferedWriter(new FileWriter("dbcpWordCount.txt"));
            Map<Character, Integer> map = new HashMap<>();
            int c = 0;
            while ((c = brf.read()) != -1){
                char ch = (char)c;
                if (map.get(ch) == null){
                    map.put(ch, 1);
                }else {
                    map.put(ch, map.get(ch) + 1);
                }

            }
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
            for(Map.Entry<Character, Integer> entry : entrySet){
                switch (entry.getKey()){
                    case ' ':
                        bwf.write("空格=" + entry.getValue());
                        break;
                    case '\t':
                        bwf.write("tab键=" + entry.getValue());
                        break;
                    case '\r':
                        bwf.write("空格=" + entry.getValue());
                        break;
                    case '\n':
                        bwf.write("换行=" + entry.getValue());
                        break;
                    default:
                        bwf.write(entry.getKey() + "="  + entry.getValue());
                        break;
                }
                bwf.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brf != null){
                try {
                    brf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bwf != null){
                try {
                    bwf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
