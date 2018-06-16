package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.hot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.hot.adapter.HotAdapter;

/**
 * Created by noel on 2018/6/9.
 */

public class HotFragment extends BasicFragment implements HotAdapter.onItemClickListener {
    private View view;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private HotAdapter hotAdapter;
    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_hot, container, false);
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
        hotAdapter = new HotAdapter();
        hotAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(hotAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.video_tab_hot));
        }
        hotAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //-----------------

    @Override
    public void onItemClick(View view, int position) {

    }
}
