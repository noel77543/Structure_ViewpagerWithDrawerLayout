package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue;

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
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.issue.adapter.IssueAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.issue.IssueDetailFragment;

/**
 * Created by noel on 2018/6/9.
 */

public class IssueFragment extends BasicFragment implements IssueAdapter.onItemClickListener {
    private View view;
    private IssueAdapter issueAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        issueAdapter = new IssueAdapter();
        issueAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(issueAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.main_tab_issue));
        }
        issueAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //---------------

    @Override
    public void onItemClick(View view, int position) {
        DetailData detailData = new DetailData();
        detailData.setIndex(position);
        detailData.setViewTitle(getString(R.string.issue_detail_title));

        Bundle bundle = new Bundle();
        bundle.putParcelable("detailData", detailData);

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("nextPage", DetailActivity.ISSUE_DETAIL);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
