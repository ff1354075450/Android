package com.example;

/**
 * Created by ff on 2017/1/19.
 */

public interface Watched {
    void addwatcher(Wacher wacher);
    void removewatcher(Wacher wacher);
    void notifywathcer(String str);
}
