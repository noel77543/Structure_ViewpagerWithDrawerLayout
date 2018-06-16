package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.adapter.HomeAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal.PersonalViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.setting.SettingViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.topic.TopicViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.adapter.VideoAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.funnay.FunnyViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.hot.HotViewPagerContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.others.OthersViewPagerContainerFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class VideoFragment extends BasicFragment{
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private View view;
    private VideoAdapter videoAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] tabNames;
    private int[] colors;
    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_video, container, false);
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
        initTabs();
    }



    //-------------

    /***
     *  初始化 tab
     */
    private void initTabs() {
        tabNames = getResources().getStringArray(R.array.video_tabs);
        colors = new int[]{R.color.main_tab_non_select, R.color.main_tab_selected, R.color.main_tab_indicator, R.color.main_tab_bg};
        fragments = new ArrayList<>();
        fragments.add(new HotViewPagerContainerFragment());
        fragments.add(new FunnyViewPagerContainerFragment());
        fragments.add(new OthersViewPagerContainerFragment());
        videoAdapter = new VideoAdapter(getFragmentManager(), fragments, tabNames);

        addTabs(tabNames, colors, TabLayout.MODE_FIXED);
        viewPager.setAdapter(videoAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //------------

    /***
     *  add tabs
     */
    public void addTabs(String[] tabNames, @ColorRes int[] colors, int mode) {
        for (String name : tabNames) {
            tabLayout.addTab(tabLayout.newTab().setText(name));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(colors[0]), getResources().getColor(colors[1]));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(colors[2]));
        tabLayout.setBackgroundColor(getResources().getColor(colors[3]));
        tabLayout.setTabMode(mode);
    }

}
