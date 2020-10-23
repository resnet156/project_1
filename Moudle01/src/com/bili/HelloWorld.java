package com.bili;

public class HelloWorld {

    /**
     * @Author 李玉龙
     * @Description //TODO
     * @Date 17:13 2020/8/17
     * @Param []
     * @return void
     **/
    public static final String BANK = "123";
    private static final int LL = 1;

    public static void main(String[] args) {
        System.out.println("奥利给！！");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
        int[] arr = new int[]{65, 48, 34, 94, 87, 34, -24, -64, 73, 1};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
    public void show(){
        System.out.println("HelloWorld");
    }
}

class Bod{

}

