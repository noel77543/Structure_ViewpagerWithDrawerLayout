package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;

/**
 * Created by noel on 2018/6/30.
 */

public class VideoDetailFragment extends BasicDetailFragment {
    private View view;

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

    }
}
