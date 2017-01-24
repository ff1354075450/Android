package self.ff.account.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ff on 2017/1/23.
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainPageAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        fragmentList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
