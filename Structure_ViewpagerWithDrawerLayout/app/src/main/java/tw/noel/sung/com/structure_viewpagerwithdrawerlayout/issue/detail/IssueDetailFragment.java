package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue.detail;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class IssueDetailFragment extends BasicFragment {
    @BindView(R.id.text_view)
    TextView textView;
    private View view;
    private Runnable runnable;
    private Handler handler;
    private int index = 1;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_issue_detail, container, false);
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

    }
    //-----------


    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    //-------

    @Override
    public void onResume() {
        super.onResume();
        index = 1;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(index));
                textView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_personal_detail_text));
                index++;
                handler.postDelayed(this, 2000);
            }
        };
        runnable.run();
    }
}
