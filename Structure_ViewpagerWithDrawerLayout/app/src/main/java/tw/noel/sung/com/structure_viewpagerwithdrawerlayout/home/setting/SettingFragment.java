package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.setting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.setting.adapter.SettingAdapter;

/**
 * Created by noel on 2018/6/9.
 */

public class SettingFragment extends BasicFragment implements SettingAdapter.onItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SettingAdapter settingAdapter;
    private View view;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_setting, container, false);
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
        settingAdapter = new SettingAdapter();
        settingAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(settingAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.home_tab_setting));
        }
        settingAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //--------------------
    @Override
    public void onItemClick(View view, int position) {

    }
}
