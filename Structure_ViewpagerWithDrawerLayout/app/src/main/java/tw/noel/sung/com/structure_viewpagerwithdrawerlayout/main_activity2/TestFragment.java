package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.main_activity2;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.adapter.HomeAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal.PersonalViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.setting.SettingViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.topic.TopicViewPagerContainerFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class TestFragment extends BasicFragment {

    @BindView(R.id.text_view_test)
    TextView textView;
    private View view;


    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, container, false);
            ButterKnife.bind(this, view);
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }


    //------------------

    /***
     *  初始化
     */
    private void init() {
        int floor = getFragmentManager().getBackStackEntryCount();
        textView.setText("第" + floor + "層");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceBasicFragment2(android.R.id.tabcontent, new TestFragment(), true);
            }
        });
    }
}
