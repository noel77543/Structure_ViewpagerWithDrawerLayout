package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicContainerFragment;

/**
 * Created by noel on 2018/6/10.
 */

public class VideoContainerFragment extends BasicContainerFragment {

    @Override
    public void init() {
        replaceBasicFragment(android.R.id.tabcontent, new VideoFragment(), false);
    }
}
