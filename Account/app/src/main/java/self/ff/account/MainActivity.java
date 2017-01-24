package self.ff.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import self.ff.account.adapter.MainPageAdapter;
import self.ff.account.fragment.MainF1;
import self.ff.account.fragment.MainF2;
import self.ff.account.fragment.MainF3;

/**
 * Created by ff on 2017/1/23.
 */

public class MainActivity extends AppCompatActivity {

    private ViewPager mviewPager;

    private List<Fragment> fragmentList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mviewPager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new MainF1());
        fragmentList.add(new MainF2());
        fragmentList.add(new MainF3());

        MainPageAdapter myapdapter = new MainPageAdapter(getSupportFragmentManager(),fragmentList);
        mviewPager.setAdapter(myapdapter);

    }
}
