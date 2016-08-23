package demo.ff.starting;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 这是一个fragement测试的简单应用
 * 1.直接在activity对应的xml文件中加入fragement,注意，需要加入id和name
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 确认 Activity 使用的布局版本包含
        // fragment_container FrameLayout
        if (findViewById(R.id.frameContent) != null) {

            // 不过，如果我们要从先前的状态还原，
            // 则无需执行任何操作而应返回
            // 否则就会得到重叠的 Fragment 。
            if (savedInstanceState != null) {
                return;
            }
            //创建一个fragement
            FirstFragement firstFragement = new FirstFragement();
            // 如果此 Activity 是通过 Intent 发出的特殊指令来启动的，
            // 请将该 Intent 的 extras 以参数形式传递给该 Fragment
            firstFragement.setArguments(getIntent().getExtras());
            // 将该 Fragment 添加到“fragment_container”FrameLayout 中
//这边fragement需要继承v4的包不然会报错
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameContent, firstFragement).commit();
        }
    }

    public void change(View view){
        SecondFragement secondFragement=new SecondFragement();

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameContent,secondFragement);
        //执行事务
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
