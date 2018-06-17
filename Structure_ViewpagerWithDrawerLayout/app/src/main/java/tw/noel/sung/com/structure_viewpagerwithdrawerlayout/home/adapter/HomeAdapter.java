package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by noel on 2018/6/9.
 */

public class HomeAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> pageList;
    private String[] pageTitle;

    public HomeAdapter(FragmentManager fragmentManager, ArrayList<Fragment> pageList, String[] pageTitle) {
        super(fragmentManager);
        this.pageList = pageList;
        this.pageTitle = pageTitle;
    }

    //-------

    @Override
    public int getCount() {
        return pageList.size();
    }
    //-------

    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    //-------

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
