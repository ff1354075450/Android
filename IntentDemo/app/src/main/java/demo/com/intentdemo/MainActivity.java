package demo.com.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String CRAZYIT_ACTION="demo.com.intentdemo.action.CRAZYIT_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.intent1);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.intent1:
                Intent intent = new Intent();
                intent.setAction(MainActivity.CRAZYIT_ACTION);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
