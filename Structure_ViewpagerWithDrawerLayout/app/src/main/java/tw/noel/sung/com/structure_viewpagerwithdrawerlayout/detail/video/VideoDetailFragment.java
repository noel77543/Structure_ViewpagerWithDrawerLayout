package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.video;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.connect.ConnectInfo;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.NaughtyPlayer;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.NaughtyPlayerView;

/**
 * Created by noel on 2018/6/30.
 */

public class VideoDetailFragment extends BasicDetailFragment {
    private View view;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.naughty_player_view)
    NaughtyPlayerView naughtyPlayerView;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_video_detail, container, false);
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

    //-----

    @Override
    public void onResume() {
        super.onResume();
        activity.textView.setText(getString(R.string.video_detail_title));
    }

    //------------------

    /***
     *  初始化
     */
    private void init() {
        naughtyPlayerView.startVideoWithUrlString(ConnectInfo.URL_HTTP_LIVE_STREAM);
    }
}
