package demo.com.draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

/**
 * Created by ff on 2016/12/22.
 */

public class DrawActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout line = new LinearLayout(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
//      获取创建的宽度和高度
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        DrawView drawView = new DrawView(this);

        line.addView(drawView);
        setContentView(line);
    }
}
