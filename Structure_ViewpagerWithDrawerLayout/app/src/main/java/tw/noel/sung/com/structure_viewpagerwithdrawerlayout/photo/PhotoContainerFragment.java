package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.photo;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicContainerFragment;
/**
 * Created by noel on 2018/6/10.
 */

public class PhotoContainerFragment extends BasicContainerFragment {

    @Override
    public void init() {
        replaceBasicFragment(android.R.id.tabcontent, new PhotoFragment(), false);
    }
}
