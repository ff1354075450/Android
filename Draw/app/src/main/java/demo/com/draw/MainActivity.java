package demo.com.draw;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openDrawView(View view){
        ComponentName componentName = new ComponentName(this,DrawActivity.class);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
