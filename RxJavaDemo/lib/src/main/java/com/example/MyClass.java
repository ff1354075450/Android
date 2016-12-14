package com.example;


/**
 * 观察者被观察着模式
 */
public class MyClass {

    public static void main(String[] args) throws Exception{


        //创建观察者
        Wacher wacher = new TestWatcher();
        Wacher wacher2 = new TestWatcher();
        Wacher wacher3 = new TestWatcher();
//创建被观察者
        Watched watched = new TestWatched();
//添加监听
        watched.addwatcher(wacher);
        watched.addwatcher(wacher2);
        watched.addwatcher(wacher3);


//更新被观察者
        watched.notifywathcer("I have changed");
    }
}
