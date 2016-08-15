package edu.basic.weixinpromot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageButton imageButton;
    private TextView textView;
    private LinearLayout mChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        textView=(TextView)findViewById(R.id.textView2);
        mChat=(LinearLayout)findViewById(R.id.chat_warp);

        /**
         * 初始化
         * setTargetView（）设置哪个控件显示数字提醒，参数就是一个view对象
         * 1. setTargetView(View) --> 设置哪个控件显示数字提醒，参数就是一个view对象
         2. setBadgeCount(int) --> 设置提醒的数字
         3. setBadgeGravity(Gravity) --> 设置badgeview的显示位置
         Gravity.BOTTOM | Gravity.CENTER 中下
         4. setBackgroundColor() --> 设置badgeview的背景色，当然还可以设置背景图片
         5. setBackgroundResource() --> 设置背景图片
         6. setTypeface() --> 设置显示的字体
         7. setShadowLayer() --> 设置字体的阴影
         *
         */

        BadgeView badgeView1=new BadgeView(this);
        badgeView1.setTargetView(button);
        badgeView1.setBadgeGravity(Gravity.BOTTOM | Gravity.CENTER);
        badgeView1.setText("测试");
//        badgeView1.setTextColor(Color.BLACK);
        badgeView1.setBadgeCount(7);
//        badgeView1.setBackgroundResource(R.drawable.xx);


    }
}
