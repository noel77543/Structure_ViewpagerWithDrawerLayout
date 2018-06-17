package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.personal.PersonalDetailFragment;

/**
 * Created by noel on 2018/6/17.
 */

public class DetailActivity extends FragmentActivity {


    @BindView(R.id.button_back)
    Button buttonBack;
    @BindView(R.id.text_view)
    public TextView textView;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;

    public static final int HOME_TOPIC_DETAIL = 1001;
    public static final int HOME_PERSONAL_DETAIL = 1002;
    public static final int HOME_SETTING_DETAIL = 1003;

    public static final int ISSUE_DETAIL = 2001;

    public static final int LIVE_DETAIL = 3001;

    public static final int VIDEO_HOT_DETAIL = 4001;
    public static final int VIDEO_FUNNY_DETAIL = 4002;
    public static final int VIDEO_OTHERS_DETAIL = 4003;

    public static final int PHOTO_DETAIL = 5001;


    @IntDef({HOME_TOPIC_DETAIL, HOME_PERSONAL_DETAIL, HOME_SETTING_DETAIL, ISSUE_DETAIL, LIVE_DETAIL, VIDEO_HOT_DETAIL, VIDEO_FUNNY_DETAIL, VIDEO_OTHERS_DETAIL, PHOTO_DETAIL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NextPage {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        goToNextPage(getIntent().getIntExtra("nextPage", 0));
    }

    //---------

    /***
     * 依照nextPage替換Fragment
     * @param nextPage
     */
    private void goToNextPage(int nextPage) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (nextPage) {
            //首頁 - 主題
            case HOME_TOPIC_DETAIL:

                break;
            //首頁 - 個人
            case HOME_PERSONAL_DETAIL:
                fragment = new PersonalDetailFragment();
                bundle.putParcelable("detailData", getIntent().getExtras().getParcelable("detailData"));
                break;
            //首頁 - 設定
            case HOME_SETTING_DETAIL:

                break;

            //議題
            case ISSUE_DETAIL:

                break;

            //直播
            case LIVE_DETAIL:

                break;

            //影音 - 熱門
            case VIDEO_HOT_DETAIL:

                break;

            //影音 - 搞笑
            case VIDEO_FUNNY_DETAIL:

                break;

            //影音 - 其他
            case VIDEO_OTHERS_DETAIL:

                break;

            //圖輯
            case PHOTO_DETAIL:

                break;
        }
        replaceFragment(fragment, bundle);
    }

    //---------

    /***
     *  替換Fragment
     * @param fragment
     * @param bundle
     */
    private void replaceFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }

    //----------------
    @OnClick(R.id.button_back)
    public void onViewClicked() {
        backToBeforePage();
    }

    //-----------------

    @Override
    public void onBackPressed() {
        backToBeforePage();
    }


    //----------------------

    /**
     * back 行為
     */
    private void backToBeforePage() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
