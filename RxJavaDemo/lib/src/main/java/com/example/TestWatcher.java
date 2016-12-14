package com.example;

/**
 * Created by ff on 2017/1/19.
 */

public class TestWatcher implements Wacher {


    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
