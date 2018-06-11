package tw.noel.sung.com.structure_viewpagerwithdrawerlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.HomeContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue.IssueContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.LiveContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.navigation.adapter.NavigationAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.navigation.collect_point.CollectActivity;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.photo.PhotoContainerFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.VideoContainerFragment;

public class MainActivity extends FragmentActivity implements NavigationAdapter.onItemClickListener, TabHost.OnTabChangeListener {

    @BindView(R.id.text_view)
    TextView textView;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private List<String> tabNames;
    private String[] navigationItems;
    private NavigationAdapter navigationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    //------------

    /***
     *  初始化
     */
    private void init() {
        initTabs();
        initNavigation();
    }


    //-----------------

    /***
     *  初始化tabs
     */
    private void initTabs() {
        tabNames = Arrays.asList(getResources().getStringArray(R.array.main_tabs));
        int[] tabImages = new int[]{R.drawable.ic_tab_home, R.drawable.ic_tab_issue, R.drawable.ic_tab_live, R.drawable.ic_tab_video, R.drawable.ic_tab_photo};
        Class[] classes = new Class[]{HomeContainerFragment.class, IssueContainerFragment.class, LiveContainerFragment.class, VideoContainerFragment.class, PhotoContainerFragment.class};
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < tabNames.size(); i++) {
            tabHost.addTab(tabHost.newTabSpec(tabNames.get(i)).setIndicator(getTabView(tabImages[i], tabNames.get(i))), classes[i], null);
        }

        textView.setText(tabNames.get(0));
        tabHost.setOnTabChangedListener(this);
    }

    //--------------

    /***
     * 當底下tab切換
     */

    @Override
    public void onTabChanged(String tabId) {
        textView.setText(tabNames.get(tabNames.indexOf(tabId)));
    }

    //-----------------

    /***
     *  初始化側邊欄項目
     */
    private void initNavigation() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        navigationAdapter = new NavigationAdapter(this);
        recyclerView.setAdapter(navigationAdapter);
        navigationItems = getResources().getStringArray(R.array.navigation_items);
        navigationAdapter.setData(navigationItems);
        navigationAdapter.setOnItemClickListener(this);
    }
    //----------------

    /***
     *  當點擊側邊欄項目
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        //關閉菜單
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (position) {
            //集點換獎品
            case 0:
                startActivity(new Intent(MainActivity.this, CollectActivity.class));
                break;
            //議題
            case 1:
                tabHost.setCurrentTab(1);
                break;
        }
    }

    //-----------------

    /***
     *  設定tab的背景圖
     * @param imgRes
     * @param title
     * @return
     */
    private View getTabView(int imgRes, String title) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_main, null);
        ImageView tab = (ImageView) view.findViewById(R.id.imageview);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tab.setImageResource(imgRes);
        tvTitle.setText(title);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 130);
        layoutParams.setMargins(3, 3, 3, 3);
        layoutParams.weight = 1;
        view.setLayoutParams(layoutParams);
        return view;
    }


    //-----------------
    @OnClick(R.id.image_view_menu)
    public void OnClicked(View view) {
        switch (view.getId()) {
            //左上角菜單
            case R.id.image_view_menu:
                //如果菜單是打開的
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                //關閉的
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
    }
    //----------------

    /**
     * 檢查tag
     */
    private boolean isTabTag(String tabName) {
        for (int i = 0; i < tabNames.size(); i++) {
            if (tabName.equals(tabNames.get(i))) {
                return true;
            }
        }
        return false;
    }

    //----------------------

    /**
     * back 行為
     */
    private void backToBeforePage() {
        boolean isPopFragment = false;
        String currentTabTag = tabHost.getCurrentTabTag();
        if (isTabTag(currentTabTag)) {
            isPopFragment = ((BasicFragment) getSupportFragmentManager()
                    .findFragmentByTag(currentTabTag)).popFragment();
        }

        if (!isPopFragment) {
            this.finish(); // 關閉
        }
    }

    //----------------------

    /**
     * back
     */
    @Override
    public void onBackPressed() {
        backToBeforePage();
    }

}
