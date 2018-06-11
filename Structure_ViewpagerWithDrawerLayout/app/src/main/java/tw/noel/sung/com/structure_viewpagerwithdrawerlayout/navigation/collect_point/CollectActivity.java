package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.navigation.collect_point;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;

/**
 * Created by noel on 2018/6/10.
 */

public class CollectActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        init();
    }

    //-------------

    /***
     * 初始化
     */
    private void init(){

    }

}
