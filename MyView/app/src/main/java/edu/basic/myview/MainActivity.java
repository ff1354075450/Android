package edu.basic.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 自定义一个view控件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
