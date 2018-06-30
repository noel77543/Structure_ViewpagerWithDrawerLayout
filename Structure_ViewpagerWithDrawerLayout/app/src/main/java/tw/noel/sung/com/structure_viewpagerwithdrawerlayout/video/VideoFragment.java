package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video;

import android.content.Intent;
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
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.DetailActivity;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.model.DetailData;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.video.adapter.VideoAdapter;


/**
 * Created by noel on 2018/6/9.
 */

public class VideoFragment extends BasicFragment implements VideoAdapter.onItemClickListener{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;
    private VideoAdapter videoAdapter;

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



    //--------

    /***
     *  初始化
     */
    private void init() {
        recyclerView.setHasFixedSize(true);

        videoAdapter = new VideoAdapter();
        videoAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(videoAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.main_tab_video));
        }
        videoAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }


    //---------------------

    /***
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        DetailData detailData = new DetailData();
        detailData.setIndex(position);
        detailData.setViewTitle(getString(R.string.personal_detail_title));

        Bundle bundle = new Bundle();
        bundle.putParcelable("detailData", detailData);

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("nextPage", DetailActivity.VIDEO_DETAIL);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}