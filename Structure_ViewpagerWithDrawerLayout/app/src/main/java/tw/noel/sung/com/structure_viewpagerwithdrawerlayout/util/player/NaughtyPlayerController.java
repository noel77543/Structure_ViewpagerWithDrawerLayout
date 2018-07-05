package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
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

public class NaughtyPlayerController extends View implements View.OnTouchListener, Runnable {

    private ControllerPopupWindow controllerPopupWindow;
    private float lastX;
    private float lastY;

    //允許值
    private int _DEFAULT_VALUE = 30;
    private Context context;
    private float viewHeight;
    private float currentAlpha = 1;

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
        controllerPopupWindow = new ControllerPopupWindow(context);

        setBackgroundColor(context.getResources().getColor(R.color.naughty_controller_bg));
        setOnTouchListener(this);
        post(this);
    }

    //-----------------
    @Override
    public void run() {
        viewHeight = getHeight();
    }
    //----------------

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float currentX = motionEvent.getX();
        float currentY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                //如果X變動值在允許值以下 表示垂直移動
                if (Math.abs((int) (lastX - currentX)) < _DEFAULT_VALUE) {
//                    controllerPopupWindow.showAsDropDown( this, -(int) currentX,- (int) currentY);
//                    Log.e("垂直移動", "垂直移動");
                    Log.e("viewHeight", ""+viewHeight);
                    Log.e("Y", ""+(lastY - currentY));

                    currentAlpha = 1 - ( 5*(lastY - currentY) / viewHeight);
                    setAlpha(currentAlpha);
                }
                //如果Y變動值在允許值以下 表示水平移動
                else if (Math.abs((int) (lastY - currentY)) < _DEFAULT_VALUE) {

                    Log.e("水平移動", "水平移動");

                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }


    //---------------

}
