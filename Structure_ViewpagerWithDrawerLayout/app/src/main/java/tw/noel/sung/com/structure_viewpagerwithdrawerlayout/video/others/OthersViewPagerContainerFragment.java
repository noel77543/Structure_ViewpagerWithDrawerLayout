package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.others;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicViewPagerContainerFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class OthersViewPagerContainerFragment extends BasicViewPagerContainerFragment {
    @Override
    public void init() {
        replaceBasicFragment(R.id.video_pager_id, new OthersFragment(), false);
    }    @Override
    public int getFrameLayoutId() {
        return R.id.video_pager_id;
    }
}
