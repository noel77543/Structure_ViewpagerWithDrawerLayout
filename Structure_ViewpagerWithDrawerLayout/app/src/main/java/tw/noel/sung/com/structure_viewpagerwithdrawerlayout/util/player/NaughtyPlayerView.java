package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by noel on 2018/7/5.
 */

public class NaughtyPlayerView extends FrameLayout {

    private Context context;

    private NaughtyPlayer naughtyPlayer;
    private FrameLayout.LayoutParams playerParams;

    private NaughtyPlayerController naughtyPlayerController;
    private FrameLayout.LayoutParams controllerParams;

    //----------
    public NaughtyPlayerView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }
    //----------

    public NaughtyPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    //----------

    public NaughtyPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    //----------
    private void init() {
        addPlayer();
        addController();
    }

    //------------
    private void addPlayer() {
        playerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        naughtyPlayer = new NaughtyPlayer(context);
        naughtyPlayer.setLayoutParams(playerParams);
        addView(naughtyPlayer);
    }

    //------------

    private void addController() {
        controllerParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        naughtyPlayerController = new NaughtyPlayerController(context);
        naughtyPlayerController.setLayoutParams(controllerParams);
        addView(naughtyPlayerController);
    }
}
