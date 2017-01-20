package demo.com.myfragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.left_frg_button);
        Button c = (Button) findViewById(R.id.left_frg_button2);
        c.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_frg_button:
                /**
                 * 点击按钮切换碎片
                 * 1.添加碎片实例
                 * 2.获取fragmentManager
                 * 3.开启一个事务，
                 * 4.向容器中添加碎片
                 * 5.允许返回
                 * 6.提交事务
                  */
                AnotherFragment fragment = new AnotherFragment();
                android.app.FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.right_layout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            case R.id.left_frg_button2:
                ListFragment listFragment= new ListFragmentD();
                getFragmentManager().beginTransaction().replace(R.id.right_layout,listFragment).commit();
                break;

                default:
                    break;

        }
    }
}
