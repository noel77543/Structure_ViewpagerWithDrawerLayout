package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.photo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.DetailActivity;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.photo.adapter.PhotoAdapter;

/**
 * Created by noel on 2018/6/9.
 */

@RuntimePermissions
public class PhotoFragment extends BasicFragment implements PhotoAdapter.onItemClickListener {
    private View view;
    private PhotoAdapter photoAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //-----------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, container, false);
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
        photoAdapter = new PhotoAdapter();
        photoAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(photoAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(getString(R.string.main_tab_photo));
        }
        photoAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
    }

    //----------

    @Override
    public void onItemClick(View view, int position) {
        PhotoFragmentPermissionsDispatcher.allowedOpenCameraWithCheck(this);
    }
    //-----------

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void allowedOpenCamera() {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("nextPage", DetailActivity.PHOTO_DETAIL);

        startActivity(intent);
    }

    //-----------

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhotoFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    //-----------

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        request.proceed();
    }
    //-----------

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        Toast.makeText(activity, getString(R.string.toast_permission_refuse), Toast.LENGTH_SHORT).show();
    }
    //-----------

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage(getString(R.string.dialog_message_goto_setting));
        alert.setPositiveButton(getString(R.string.dialog_go), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent settings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
                settings.addCategory(Intent.CATEGORY_DEFAULT);
                settings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(settings);
            }
        });
        alert.show();
    }
}
