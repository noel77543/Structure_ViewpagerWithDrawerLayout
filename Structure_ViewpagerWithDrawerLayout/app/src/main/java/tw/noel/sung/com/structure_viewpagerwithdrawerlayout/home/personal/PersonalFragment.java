package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.DetailActivity;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.model.DetailData;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal.adapter.PersonAdapter;

/**
 * Created by noel on 2018/6/9.
 */

public class PersonalFragment extends BasicFragment implements PersonAdapter.onItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private View view;
    private PersonAdapter personAdapter;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personal, container, false);
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

        personAdapter = new PersonAdapter(activity);

        personAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(personAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.home_tab_personal));
        }
        personAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }


    public TextView getFirstTextView(){
        PersonAdapter.ViewHolder viewHolder = (PersonAdapter.ViewHolder)recyclerView.findViewHolderForAdapterPosition(0);
        return viewHolder.textView;
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
        intent.putExtra("nextPage", DetailActivity.HOME_PERSONAL_DETAIL);
        intent.putExtras(bundle);

        startActivity(intent);

    }


}
