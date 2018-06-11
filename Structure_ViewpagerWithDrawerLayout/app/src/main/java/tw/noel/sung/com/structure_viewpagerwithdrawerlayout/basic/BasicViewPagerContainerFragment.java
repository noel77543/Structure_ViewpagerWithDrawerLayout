package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;


/**
 * Created by noel on 2018/6/9.
 */

public abstract class BasicViewPagerContainerFragment extends BasicFragment {
    //view 是否已經生成
    private boolean isViewCreated;
    //替換用FrameLayout
    private FrameLayout frameLayout;

    //     ---------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return getFrameLayout();
    }

    //---------------------------------------------------

    /***
     *  動態生成FrameLayout
     */
    private FrameLayout getFrameLayout() {
        frameLayout = new FrameLayout(activity);
        frameLayout.setId(R.id.home_pager_id);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return frameLayout;
    }


    // ---------------------------------------------------
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!isViewCreated) {
            isViewCreated = true;
            init();
        }
    }

    // ---------------------------------------------------
    public abstract void init();
}
