package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.adapter.LiveAdapter;

/**
 * Created by noel on 2018/6/9.
 */

public class LiveFragment extends BasicFragment implements LiveAdapter.onItemClickListener {
    private View view;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private LiveAdapter liveAdapter;
    private final String YOUTUBE_ID = "pb-kc6DWIDI";
    private YouTubePlayer youTubePlayer;

    private ArrayList<String> data = new ArrayList<>();
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_live, container, false);
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
        liveAdapter = new LiveAdapter();
        liveAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(liveAdapter);

        for (int i = 0; i < 50; i++) {
            data.add(YOUTUBE_ID);
        }
        liveAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //----------

    @Override
    public void onItemClick(View view, final int position) {
        //解除其他撥放的
        if (youTubePlayer != null) {
            youTubePlayer.release();
            getFragmentManager().beginTransaction().remove(youTubePlayerSupportFragment).commit();
        }

        youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
        replaceBasicFragment2(view.getId(), youTubePlayerSupportFragment, false);
        youTubePlayerSupportFragment.initialize(getString(R.string.youtube_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                LiveFragment.this.youTubePlayer = youTubePlayer;
                youTubePlayer.loadVideo(data.get(position));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
