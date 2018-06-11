package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue.detail.IssueDetailFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class IssueFragment extends BasicFragment {
    private View view;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_issue, container, false);
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
    @OnClick(R.id.text_view)
    public void OnClicked(View view){
        switch (view.getId()){
            case R.id.text_view:
                replaceBasicFragment2(android.R.id.tabcontent,new IssueDetailFragment(),true);
                break;
        }
    }
}
