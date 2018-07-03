package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.basic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by noel on 2018/6/15.
 */

public abstract class BasicPopupWindow extends PopupWindow {
    private Context context;
    private View contentView;

    public BasicPopupWindow(Context context) {

        contentView = LayoutInflater.from(context).inflate(getContentLayoutId(), null);
        setContentView(contentView);
        initContentView(contentView);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(getBackgroundDrawable());
        setOutsideTouchable(true);
        setFocusable(true);

    }

    //----------

    /***
     *  設置layout ID
     * @return
     */
    protected abstract int getContentLayoutId();
    //----------

    /***
     *find view by id
     */
    protected abstract void initContentView(View view);

    //---------

    protected abstract Drawable getBackgroundDrawable();

}
