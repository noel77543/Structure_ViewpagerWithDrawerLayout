package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicViewPagerContainerFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class PersonalViewPagerContainerFragment extends BasicViewPagerContainerFragment {
    @Override
    public void init() {
        replaceBasicFragment(R.id.home_pager_id, new PersonalFragment(), false);
    }
    @Override
    public int getFrameLayoutId() {
        return R.id.home_pager_id;
    }
}
