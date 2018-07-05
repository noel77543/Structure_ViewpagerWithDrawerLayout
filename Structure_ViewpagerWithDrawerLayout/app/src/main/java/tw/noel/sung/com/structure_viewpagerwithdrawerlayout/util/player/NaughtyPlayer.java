package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.popupwindow.ControllerPopupWindow;

public class NaughtyPlayer extends VideoView implements View.OnTouchListener {

    private Context context;
    private ControllerPopupWindow controllerPopupWindow;
    private float lastX;
    private float lastY;

    //允許值
    private int _DEFAULT_VALUE = 20;

    public NaughtyPlayer(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NaughtyPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public NaughtyPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //--------
    private void init() {
        controllerPopupWindow = new ControllerPopupWindow(context);
        setOnTouchListener(this);
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
                    controllerPopupWindow.showAsDropDown( this, -(int) currentX,- (int) currentY);
                    Log.e("垂直移動", "垂直移動");

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
    //----------------


    //----------------

}
