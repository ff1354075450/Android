package edu.basic.scrollviewkeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
    }
    public void next(View view){
        Intent intent=new Intent(this,NextActivity.class);
        startActivity(intent);
    }
}
