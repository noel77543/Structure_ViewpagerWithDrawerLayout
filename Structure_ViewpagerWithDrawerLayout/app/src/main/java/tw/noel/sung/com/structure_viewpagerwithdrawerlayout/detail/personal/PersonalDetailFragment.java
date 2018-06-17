package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.model.DetailData;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.personal.regard.PersonalRegardFragment;

/**
 * Created by noel on 2018/6/17.
 */

public class PersonalDetailFragment extends BasicDetailFragment {
    @BindView(R.id.text_view)
    TextView textView;
    private View view;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personal_detail, container, false);
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

    @Override
    public void onResume() {
        super.onResume();
        DetailData detailData = getArguments().getParcelable("detailData");

        String text = String.format(getString(R.string.personal_detail_text), detailData.getViewTitle(), detailData.getIndex());
        textView.setText(text);
        activity.textView.setText(detailData.getViewTitle());

    }

    //------------------

    /***
     *  初始化
     */
    private void init() {

    }

    //---------
    @OnClick(R.id.text_view)
    public void OnClicked() {
        replaceDetailFragment(R.id.frame_layout, new PersonalRegardFragment(), true);

    }
}
