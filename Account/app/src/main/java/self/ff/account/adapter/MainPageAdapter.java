package self.ff.account.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ff on 2017/1/23.
 */

public class MainPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fms;
    private FragmentManager fm;

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        fms = fm.getFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return fms.get(position);
    }

    @Override
    public int getCount() {
        return fms.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object==view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fms.get(position).onDestroy();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        等待修改
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
