package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicContainerFragment;

/**
 * Created by noel on 2018/6/10.
 */

public class IssueContainerFragment extends BasicContainerFragment {

    @Override
    public void init() {
        replaceBasicFragment(android.R.id.tabcontent, new IssueFragment(), false);
    }
}
