package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.MainActivity;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.adapter.LiveAdapter;

/**
 * Created by noel on 2018/6/9.
 */

public class LiveFragment extends BasicFragment implements LiveAdapter.OnPlayButtonClickListener, YouTubePlayer.OnFullscreenListener, MainActivity.OnYoutubePlayerFullScreenListener {
    private final int NONE_PLAYING = -1;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private View view;
    private LiveAdapter liveAdapter;
    private YouTubePlayer youTubePlayer;
    private ArrayList<String> datas = new ArrayList<>();
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private LinearLayoutManager linearLayoutManager;
    private int playingItemIndex = NONE_PLAYING;

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
        activity.setOnOnYoutubePlayerFullScreenListener(this);
        liveAdapter = new LiveAdapter(activity);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        liveAdapter.setOnPlayButtonClickListener(this);
        recyclerView.setAdapter(liveAdapter);
        datas.add("VhHQyb4r9Xg");
        datas.add("pb-kc6DWIDI");
        datas.add("_l-Mtr-lCAU");
        datas.add("FuDdstaZXdw");
        datas.add("w_DjvLKuHGc");
        datas.add("5DKNU34L5DA");
        liveAdapter.setData(datas);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (playingItemIndex != NONE_PLAYING) {
                    View item = linearLayoutManager.findViewByPosition(playingItemIndex);
                    if (item != null && isScrolledOver(dy, item)) {
                        releasePlayer();
                        playingItemIndex = NONE_PLAYING;
                    }
                }
            }
        });
    }
    //----------

    /***
     *  清單由下往上滾動 且 view上端超過高度的五分之一被遮擋
     *  或者
     *  清單由上網下滾動 且 view下端超過高度的五分之一被遮擋
     * @param dy
     * @param view
     * @return
     */
    private boolean isScrolledOver(int dy, View view) {
        return (dy > 0 && view.getTop() < -view.getHeight() / 5) || (dy < 0 && (view.getTop()) > 6 * view.getHeight() / 5);
    }


    //----------

    @Override
    public void onPlayButtonClicked(View view, final int position) {
        releasePlayer();

        youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
        replaceBasicFragment2(view.getId(), youTubePlayerSupportFragment, false);
        youTubePlayerSupportFragment.initialize(getString(R.string.youtube_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                playingItemIndex = position;
                LiveFragment.this.youTubePlayer = youTubePlayer;
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
                youTubePlayer.setOnFullscreenListener(LiveFragment.this);
                youTubePlayer.loadVideo(datas.get(position));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    //------

    /***
     *  釋放player
     */
    private void releasePlayer() {
        if (youTubePlayer != null) {
            youTubePlayer.release();
            getFragmentManager().beginTransaction().remove(youTubePlayerSupportFragment).commit();
        }
    }

    //---------

    @Override
    public void onFullscreen(boolean isFullScreen) {
        activity.setFullScreen(isFullScreen);
    }
    //---------

    @Override
    public void onYoutubePlayerFullScreen() {
        if (youTubePlayer != null) {
            youTubePlayer.setFullscreen(false);
        }
    }
}
