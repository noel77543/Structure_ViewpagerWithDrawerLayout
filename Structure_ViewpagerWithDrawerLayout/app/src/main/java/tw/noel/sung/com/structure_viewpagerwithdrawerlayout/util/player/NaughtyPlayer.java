package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.util.player.popupwindow.ControllerPopupWindow;

public class NaughtyPlayer extends VideoView implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private Context context;
    //目前撥放時間 ( 1/1000秒 )
    private int playerCurrentTimeMillis;
    //影片總長度 ( 1/1000秒 )
    private int playerTotalTimeMillis;

    private MediaPlayer mediaPlayer;

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
        setOnPreparedListener(this);
    }
    //--------

    /***
     *  當影片家載完成即將開始播放
     * @param mediaPlayer
     */
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playerTotalTimeMillis = mediaPlayer.getDuration();
        this.mediaPlayer = mediaPlayer;
        this.mediaPlayer.setOnBufferingUpdateListener(this);
        Log.e("onPrepared", "" + playerTotalTimeMillis);
    }
    //---------

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {

        Log.e("buffer second", "" + (playerTotalTimeMillis * percent / 100));

    }
}
