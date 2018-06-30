package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * Created by noel on 2018/6/30.
 */

public class CustomPlayerView extends FrameLayout implements Runnable, CustomController.OnRollBarScrollListener, CustomController.OnRollDirectionChangedListener {
    private Context context;
    private LinearLayout firstLayout;
    private CustomController customController;
    //單次Margin增減量
    private final int MARGIN_VARIABLE = 30;

    //拖曳條的高度 預設 50px
    private int swipeBarHeight = 50;
    private int maxTopMargin;
    private FrameLayout.LayoutParams layoutParams;
    private Handler animationHandler;
    private int rollDirection;
    //滾動初始時間
    private int animationTime;
    //runable開關
    private boolean isStop;


    public CustomPlayerView(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public CustomPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public CustomPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //-------------------

    private void init() {
        animationHandler = new Handler();
        initLayouts();
    }

    //-------------------

    /***
     * 加入Layout
     */
    private void initLayouts() {
        firstLayout = new LinearLayout(context);
        firstLayout.setOrientation(LinearLayout.VERTICAL);
        firstLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        firstLayout.setId(generateViewId());
        firstLayout.setBackgroundColor(context.getResources().getColor(android.R.color.black));
        addView(firstLayout);

        firstLayout.post(this);
    }


    //-------------------

    /***
     *  第一層 layout 建立好後 接著處里第二層
     */
    @Override
    public void run() {
        swipeBarHeight = firstLayout.getHeight() / 25;
        maxTopMargin = firstLayout.getHeight() - swipeBarHeight;

        customController = new CustomController(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, firstLayout.getHeight() + swipeBarHeight);
        layoutParams.setMargins(0, firstLayout.getHeight() - swipeBarHeight, 0, 0);
        customController.setLayoutParams(layoutParams);
        customController.setId(generateViewId());
        customController.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        customController.setOnRollBarScrollListener(this);
        customController.setOnRollDirectionChangedListener(this);

        this.layoutParams = layoutParams;
        addView(customController);
    }
    //-------------------

    @Override
    public void OnRollBarScroll(float distance) {
        float newTopMargin = layoutParams.topMargin + distance;
        if (newTopMargin <= 0) {
            //如果 layoutParams.topMargin += 移動值 會小於等於0  則  layoutParams.topMargin = 0
            layoutParams.topMargin = 0;
        } else if (newTopMargin >= maxTopMargin) {
            //如果 layoutParams.topMargin += 移動值 會大於等於 layoutHeight 則  layoutParams.topMargin = layoutHeight
            layoutParams.topMargin = maxTopMargin;
        } else {
            //一般情況 layoutParams.topMargin += 移動值
            layoutParams.topMargin += distance;
        }
        //更新Layout
        requestLayout();
    }


    //--------------------

    /***
     * 每當方向改變 animationTime刷新為100
     *  執行滾動動畫
     */
    @Override
    public void onRollDirectionChanged(int rollDirection) {
        animationTime = 100;
        startAnimationWithDirection(rollDirection);
    }

    //------------

    /***
     *   進行animationRun
     * @param rollDirection
     */
    private void startAnimationWithDirection(int rollDirection) {
        this.rollDirection = rollDirection;
        animationRun.run();
    }

    //------------------------
    /***
     *  固定每次增減 50 , 而時間間隔持續除以10, 使增減 50 的行為越來越快 直到 到頂 或到底 或方向改變
     */
    Runnable animationRun = new Runnable() {
        @Override
        public void run() {
            animationTime /= 10;
            switch (rollDirection) {
                //往上
                case CustomController.ROLL_UP:
                    if (layoutParams.topMargin - MARGIN_VARIABLE < 0) {
                        layoutParams.topMargin = 0;
                        isStop = true;
//                        if (onSecondViewShowListener != null) {
//                            onSecondViewShowListener.OnSecondViewShow();
//                        }
                    } else {
                        layoutParams.topMargin -= MARGIN_VARIABLE;
                        isStop = false;
                    }
                    break;
                //往下
                default:
                case CustomController.ROLL_DOWN:
                    if (layoutParams.topMargin + MARGIN_VARIABLE > maxTopMargin) {
                        layoutParams.topMargin = maxTopMargin;
                        isStop = true;
//                        if (onFirstViewShowListener != null) {
//                            onFirstViewShowListener.OnFirstViewShow();
//                        }
                    } else {
                        layoutParams.topMargin += MARGIN_VARIABLE;
                        isStop = false;
                    }
                    break;
            }
            requestLayout();


            if (isStop) {
                animationHandler.removeCallbacks(animationRun);
            } else {
                animationHandler.postDelayed(this, animationTime);
            }
        }
    };


    //-------------public---method------

    /***
     *  用來加入View進去第一個的LinearLayout
     * @param view
     */
    public void addFirstView(View view) {
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        firstLayout.addView(view);
    }

    //-------------------

    /***
     *  用來加入View進去第二個的LinearLayout
     * @param view
     */
    public void addSecondView(View view) {
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        customController.addView(view);
    }


}
