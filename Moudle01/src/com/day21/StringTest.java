package com.day21;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/21 15:42
 * @Version 1.0
 **/
public class StringTest {
    public static void main(String[] args) {

    }
    //方式一
    public String reverse(String str, int startIndex, int endIndex){
        if(str == null){
            char[] chars = str.toCharArray();
            for (int x = startIndex, y = endIndex; x < y; x ++, y --) {
                char c = chars[x];
                chars[x] = chars[y];
                chars[y] = c;
            }
            return chars.toString();
        }return null;
    }
    //方拾二
    public String reverse1(String str, int startIndex, int endIndex){
        String s = str.substring(0, startIndex);
        for (int i = endIndex; i >= startIndex; i--) {
            s += str.charAt(i);
        }
        s += str.substring(endIndex + 1);
        return s;
    }
    //方式三
    public String reverse2(String str, int startIndex, int endIndex){
        StringBuilder stringBuilder = new StringBuilder(str.length());
        stringBuilder.append(str.substring(0, startIndex));
        for (int i = endIndex; i >= startIndex; i--) {
            stringBuilder.append(str.charAt(i));
        }
        stringBuilder.append(str.substring(endIndex + 1));
        return new String(stringBuilder);
    }
/*    public int getCount(String mainstr, String substr){
        int lengthStart = mainstr.length();
        int lengthSub = substr.length();
        int count = 0;
        int index;
        if(lengthStart > lengthSub){
            while((index = mainstr.indexOf(substr))!= -1){
                count ++;
                mainstr = mainstr.substring(index + lengthSub);
            }
        }else{
            return 0;
        }

    }*/
}
