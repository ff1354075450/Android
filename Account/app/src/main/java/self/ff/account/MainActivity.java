package self.ff.account;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ff on 2017/1/23.
 */

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        setContentView(R.layout.main);
        super.onCreate(savedInstanceState, persistentState);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter();
    }
}
