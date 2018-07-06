package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.popupwindow.ControllerPopupWindow;

/**
 * Created by noel on 2018/7/5.
 */

public class NaughtyPlayerController extends View implements View.OnTouchListener, Runnable, View.OnClickListener {

    private final float _MAX_ALPHA_VALUE = 1;
    private final float _MIN_ALPHA_VALUE = 0.1f;

    private float currentAlpha = _MIN_ALPHA_VALUE;


    private ControllerPopupWindow controllerPopupWindow;
    private float lastX;
    private float lastY;
    private float previousY;
    private float previousX;

    //允許值
    private int _DEFAULT_VALUE = 30;
    private Context context;
    private float viewHeight;
    private float viewWidth;

    //音量服務
    private AudioManager audioManager;
    //震動服務
    private Vibrator vibrator;
    //裝置音量最大值
    private int maxVolume;
    //裝置目前音量
    private int currentVolume;

    private OnHorizontalSlideListener onHorizontalSlideListener;
    private boolean isVideoPrepared;


    //-------
    public NaughtyPlayerController(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NaughtyPlayerController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public NaughtyPlayerController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //-----------

    /***
     *  初始
     */
    private void init() {
        vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        controllerPopupWindow = new ControllerPopupWindow(context);
        setBackgroundColor(context.getResources().getColor(R.color.naughty_controller_bg));
        setAlpha(currentAlpha);
        setOnClickListener(this);
        setOnTouchListener(this);
        post(this);
    }

    //------

    @Override
    public void onClick(View view) {
        vibrator.vibrate(1000);
    }


    //-----------------
    @Override
    public void run() {
        viewHeight = getHeight();
        viewWidth = getWidth();
    }


    //----------------

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float currentX = motionEvent.getX();
        float currentY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                previousX = currentX;
                lastY = currentY;
                previousY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                //如果X變動值在允許值以下 表示垂直移動
                if (Math.abs((int) (lastX - currentX)) < _DEFAULT_VALUE) {
//                    controllerPopupWindow.showAsDropDown( this, -(int) currentX,- (int) currentY);

                    //Y當前位移值
                    float moveValueY = lastY - currentY;

                    //當 由上往下時  方向改變為 由下往上
                    if (currentY > lastY && previousY > currentY) {
                        lastY = previousY;
                    }
                    //當 由下往上時  方向改變為 由上往下
                    else if (currentY < lastY && previousY < currentY) {
                        lastY = previousY;
                    }
                    //記錄行為後的currentY 做為下次判斷前一次Y所在位置用
                    previousY = currentY;

                    //在螢幕左側 亮度調整
                    if (lastX < viewWidth / 2) {
                        moveValueY = moveValueY / viewHeight / 5;

                        currentAlpha = (currentAlpha - moveValueY) > _MAX_ALPHA_VALUE ? _MAX_ALPHA_VALUE : (currentAlpha - moveValueY) < _MIN_ALPHA_VALUE ? _MIN_ALPHA_VALUE : (currentAlpha - moveValueY);
                        setAlpha(currentAlpha);
                    }
                    //螢幕右側 聲音調整
                    else {
                        moveValueY = moveValueY / maxVolume / 10;

                        currentVolume = (int) ((currentVolume + moveValueY) > maxVolume ? maxVolume : (currentVolume + moveValueY) < 0 ? 0 : (currentVolume + moveValueY));
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
                    }
                }
                //如果Y變動值在允許值以下 表示水平移動
                else if (Math.abs((int) (lastY - currentY)) < _DEFAULT_VALUE) {

                    float moveValueX = ((lastX + currentX) / viewWidth) * 500;

                    //當 由左往右時  方向改變為 由右往左
                    if (currentX > lastX && previousX > currentX) {
                        lastX = previousX;
                    }
                    //當 由右往左時  方向改變為 由左往右
                    else if (currentX < lastX && previousX < currentX) {
                        lastX = previousX;
                    }
                    //記錄行為後的currentX 做為下次判斷前一次X所在位置用
                    previousX = currentX;

                    if (onHorizontalSlideListener != null && isVideoPrepared) {
                        onHorizontalSlideListener.onHorizontalSliding(moveValueX);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                if (onHorizontalSlideListener != null && isVideoPrepared) {
                    onHorizontalSlideListener.onHorizontalSlided();
                }
                break;
        }

        return true;
    }


    //---------------

    /***
     * 水平滾動 接口
     */
    public interface OnHorizontalSlideListener {
        //水平滾動中
        void onHorizontalSliding(float moveValueX);

        //水平滾動完畢
        void onHorizontalSlided();
    }

    public void setOnHorizontalSlideListener(OnHorizontalSlideListener onHorizontalSlideListener) {
        this.onHorizontalSlideListener = onHorizontalSlideListener;
    }

    //-------

    /***
     *  撥放器是否完成準備
     * @return
     */
    public boolean isVideoPrepared() {
        return isVideoPrepared;
    }

    public void setVideoPrepared(boolean videoPrepared) {
        isVideoPrepared = videoPrepared;
    }
}
