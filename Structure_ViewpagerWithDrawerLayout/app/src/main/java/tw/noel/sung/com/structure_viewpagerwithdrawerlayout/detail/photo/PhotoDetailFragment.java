package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.photo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic.BasicDetailFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.ImageUtil;

@RuntimePermissions
public class PhotoDetailFragment extends BasicDetailFragment implements SurfaceHolder.Callback, Camera.PictureCallback {


    @BindView(R.id.surface_view)
    SurfaceView surfaceView;
    @BindView(R.id.btn_take_picture)
    Button btnTakePicture;
    private static final String _DIRECTORY = "MyTestPictureDirectory";
    private View view;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private File currentImageFile;
    private ImageUtil imageUtil;

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
        PhotoDetailFragmentPermissionsDispatcher.onStartCameraWithCheck(this);
    }
    //------------------

    /***
     *  當權限允許
     */
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onStartCamera() {
        imageUtil = new ImageUtil();

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    //---------

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open();
        camera.setDisplayOrientation(getDisplayOrientation());
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    //---------

    /***
     * 相機預覽的旋轉角度需要根據相機預覽目前的旋轉角度，以及屏幕的旋轉角度計算得到
     * @return
     */
    private int getDisplayOrientation() {
        Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        Camera.CameraInfo camInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, camInfo);

        return (camInfo.orientation - degrees + 360) % 360;
    }

    //---------

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //聚焦
        camera.autoFocus(null);
    }
    //---------

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    //------------------

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhotoDetailFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    //------------------

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        request.proceed();
    }

    //------------------

    /***
     *  拒絕權限
     */
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        btnTakePicture.setEnabled(false);
        Toast.makeText(activity, getString(R.string.toast_permission_refuse), Toast.LENGTH_SHORT).show();
    }

    //------------------

    /***
     *  當選擇不再詢問
     */
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

    //--------

    /***
     *  destroy的時候確認如果檔案還存在則清除
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        clearPhotoFile();

    }

    //------

    /***
     *  清除圖片檔
     */
    private void clearPhotoFile() {
        if (currentImageFile != null && currentImageFile.exists()) {
            currentImageFile.delete();
        }
    }
    //------------

    /***
     * 保存圖片 每次保存前清除前一張圖片
     */
    private void savePicture() {

        clearPhotoFile();
        //聚焦
        camera.autoFocus(null);
        //拍照 進入callBak進行存檔
        camera.takePicture(null, null, this);
    }

    //--------------
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {

            File dir = new File(Environment.getExternalStorageDirectory(), _DIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }

            data = imageUtil.setQuality(imageUtil.rotate(imageUtil.toBitmap(data), getDisplayOrientation()), 50).toByteArray();
            currentImageFile = new File(dir, String.format(getString(R.string.file_name), System.currentTimeMillis() + ""));
            OutputStream outputStream = new FileOutputStream(currentImageFile);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //完成後再次開啟鏡頭
        camera.startPreview();
    }

    //--------------

    @OnClick(R.id.btn_take_picture)
    public void onViewClicked() {
        savePicture();
    }
}
