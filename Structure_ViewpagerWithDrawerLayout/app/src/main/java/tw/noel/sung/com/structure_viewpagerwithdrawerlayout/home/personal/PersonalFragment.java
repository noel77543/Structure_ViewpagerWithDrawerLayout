package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal;

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
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal.adapter.PersonAdapter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.home.personal.detail.PersonalDetailFragment;

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
        personAdapter = new PersonAdapter();
        personAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(personAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.home_tab_personal));
        }
        personAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //---------------------

    /***
     *  注意 點擊事件 替換的區域
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        //注意兩個method調用的差別!  以及back事件的差異
//        replaceBasicFragment2(R.id.home_pager_id, new PersonalDetailFragment(), true);
        replaceParentFragment(android.R.id.tabcontent, new PersonalDetailFragment(), true);

    }
}
