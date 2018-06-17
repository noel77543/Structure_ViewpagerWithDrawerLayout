package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.personal.regard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;

/**
 * Created by noel on 2018/6/17.
 */

public class PersonalRegardFragment extends BasicDetailFragment {
    @BindView(R.id.text_view)
    TextView textView;
    private View view;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personal_regard, container, false);
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
    //---------

    @Override
    public void onResume() {
        super.onResume();
        activity.textView.setText("AAAAAAAAA");

    }

    //---------
    private void init() {
        textView.setText("QQQQQQQQQQQQQQQQQQQQQQQQQ");
    }
}
