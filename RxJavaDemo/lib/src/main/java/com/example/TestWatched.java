package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ff on 2017/1/19.
 */

public class TestWatched implements Watched {

    List<Wacher> list = new ArrayList<>();

    @Override
    public void addwatcher(Wacher wacher) {
            list.add(wacher);
    }

    @Override
    public void removewatcher(Wacher wacher) {
            list.remove(wacher);
    }

    @Override
    public void notifywathcer(String str) {
        for(Wacher wacher:list){
            wacher.update(str);
        }
    }
}
