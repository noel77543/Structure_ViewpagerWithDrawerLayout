package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.surfaceview;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.photo.PhotoDetailFragment;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.event.EventCenter;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.ImageUtil;

/**
 * Created by noel on 2018/7/2.
 */
public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback {

    private @PhotoDetailFragment.ImageQuality
    int imageQuality = PhotoDetailFragment.QUALITY_10;

    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private Context context;
    private File currentImageFile;
    private ImageUtil imageUtil;
    private String directory;
    private String fileName;


    public CustomSurfaceView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    //---------

    /**
     * 開啟相機
     * ps 注意權限 camera 與 write external
     */
    public void init() {
        imageUtil = new ImageUtil();

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    //---------

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        camera.setDisplayOrientation(getDisplayOrientation());
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();
                }
            }
        });
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }


    //-------
    /***
     * 相機預覽的旋轉角度需要根據相機預覽目前的旋轉角度，以及屏幕的旋轉角度計算得到
     * @return
     */
    private int getDisplayOrientation() {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
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

    //------

    /***
     *  拍照
     */
    public void takePicture(@PhotoDetailFragment.ImageQuality int imageQuality, String directory, String fileName) {
        this.imageQuality = imageQuality;
        this.directory = directory;
        this.fileName = fileName;
        clearPhotoFile();

        //拍照 進入callBak進行存檔
        camera.takePicture(null, null, this);
    }
    //------

    /***
     * FaceRecognition
     *  拍照callBack
     * @param data
     * @param camera
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {

            File dir = new File(Environment.getExternalStorageDirectory(), directory);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //進行圖片轉向 與 畫值調整
            data = imageUtil.setQuality(imageUtil.rotate(imageUtil.toBitmap(data), getDisplayOrientation()), imageQuality).toByteArray();
            currentImageFile = new File(dir, String.format(context.getString(R.string.file_name), fileName));
            OutputStream outputStream = new FileOutputStream(currentImageFile);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventCenter.getInstance().sendPhotoFile(EventCenter.EVENT_FILE, currentImageFile);
        //完成後再次開啟鏡頭
        camera.startPreview();
    }
    //------

    /***
     *  清除圖片檔
     */
    public void clearPhotoFile() {
        if (currentImageFile != null && currentImageFile.exists()) {
            currentImageFile.delete();
        }
    }
}
