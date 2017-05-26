package com.yunmin.activity;

/**
 * Created by luoyunmin on 2017/3/26.
 */

public class Test {

    //;:
    public static void main(String[] args) {
        int index = 0;
        for (int i = 0; i < 11; i++) {
            index = (int) (Math.random() * 4 * 4);
            System.out.println(index);
        }

        int j=5/4;
        System.out.println("j="+j);
        System.out.println(j%2);
    }
}
