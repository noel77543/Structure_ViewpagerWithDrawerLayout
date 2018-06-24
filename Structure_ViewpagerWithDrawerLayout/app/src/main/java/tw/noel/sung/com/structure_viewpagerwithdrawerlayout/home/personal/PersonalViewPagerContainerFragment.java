package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal;

import android.util.Log;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.HomeFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class PersonalViewPagerContainerFragment extends BasicViewPagerContainerFragment {


    public PersonalViewPagerContainerFragment() {
    }


    @Override
    public void init() {
        replaceBasicFragment(R.id.home_pager_id, new PersonalFragment(), false);
    }

    @Override
    public int getFrameLayoutId() {
        return R.id.home_pager_id;
    }


}
