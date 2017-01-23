package demo.com.transitionanimation2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void transition(View view){
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        ActivityCompat.startActivity(this,new Intent(this,Activity2.class),compat.toBundle());

    }
}
