package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by noel on 2018/6/30.
 */

public class CustomController extends LinearLayout {

    //滑動方向 向上 向下
    public static final int ROLL_UP = 9487;
    public static final int ROLL_DOWN = 7777;

    @IntDef({ROLL_UP, ROLL_DOWN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RollDirection {

    }

    private OnRollBarScrollListener onRollBarScrollListener;
    private OnRollDirectionChangedListener onRollDirectionChangedListener;
    private int lastY;
    private int lastRawY;
    private
    @RollDirection
    int rollDirection = ROLL_DOWN;

    public CustomController(Context context) {
        super(context);
        init();
    }
    //--------

    public CustomController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //--------

    public CustomController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //-----

    /***
     *  init
     */
    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);

    }


    //--------
    public boolean onTouchEvent(MotionEvent event) {
        //縱坐標
        int y = (int) event.getY();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                lastRawY = rawY;
                break;
            //當手指移動 調整SecondLayout的Top Margin
            case MotionEvent.ACTION_MOVE:
                onRollBarScrollListener.OnRollBarScroll(y - lastY);
                break;
            //當手指抬起
            case MotionEvent.ACTION_UP:
                //手指抬起時的Y座標大於手指落下時候的座標表示往下滑動,反之往上
                rollDirection = rawY > lastRawY ? ROLL_DOWN : ROLL_UP;
                //滑動方向改變
                onRollDirectionChangedListener.onRollDirectionChanged(rollDirection);
                break;
        }
        return true;
    }

    //---------

    public interface OnRollBarScrollListener {
        void OnRollBarScroll(float distance);
    }
    //---------

    public void setOnRollBarScrollListener(OnRollBarScrollListener onRollBarScrollListener) {
        this.onRollBarScrollListener = onRollBarScrollListener;
    }
    //---------

    public interface OnRollDirectionChangedListener {
        void onRollDirectionChanged(@CustomController.RollDirection int scrollDirection);
    }
    //---------

    public void setOnRollDirectionChangedListener(OnRollDirectionChangedListener onRollDirectionChangedListener) {
        this.onRollDirectionChangedListener = onRollDirectionChangedListener;
    }

}
