package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.photo;


import android.os.Bundle;

import android.support.annotation.IntDef;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.surfaceview.CustomSurfaceView;

public class PhotoDetailFragment extends BasicDetailFragment{

    public static final int QUALITY_10 = 10;
    public static final int QUALITY_50 = 50;
    public static final int QUALITY_80 = 80;
    public static final int QUALITY_100 = 100;
    private @ImageQuality
    int imageQuality = QUALITY_10;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({QUALITY_10, QUALITY_50, QUALITY_80, QUALITY_100})
    public @interface ImageQuality {
    }

    @BindView(R.id.surface_view)
    CustomSurfaceView surfaceView;
    @BindView(R.id.btn_take_picture)
    Button btnTakePicture;

    private static final String _DIRECTORY = "MyTestPictureDirectory";
    private View view;

    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo_detail, container, false);
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

    @Override
    public void onResume() {
        super.onResume();
        activity.textView.setText(getString(R.string.photo_detail_title));

    }

    //------------------

    /***
     *  初始化
     */
    private void init() {
        btnTakePicture.setEnabled(true);
    }

    //--------

    /***
     *  destroy的時候確認如果檔案還存在則清除
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        surfaceView.clearPhotoFile();
    }

    //--------------

    @OnClick(R.id.btn_take_picture)
    public void onViewClicked() {
        surfaceView.takePicture(imageQuality, _DIRECTORY, System.currentTimeMillis() + "");
    }
}
