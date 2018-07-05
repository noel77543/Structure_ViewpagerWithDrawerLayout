package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.popupwindow.ControllerPopupWindow;

public class NaughtyPlayer extends VideoView  {

    private Context context;


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
       setBackgroundColor(Color.GREEN);
    }


    //----------------


    //----------------

}
