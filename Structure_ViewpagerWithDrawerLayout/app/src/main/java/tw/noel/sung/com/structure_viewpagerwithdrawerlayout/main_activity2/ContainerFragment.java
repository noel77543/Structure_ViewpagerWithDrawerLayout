package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.main_activity2;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicContainerFragment;

public class ContainerFragment extends BasicContainerFragment {

    @Override
    public void init() {
        replaceBasicFragment(android.R.id.tabcontent, new TestFragment(), false);
    }
}