package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.model.Live;

import static android.view.View.generateViewId;

/**
 * Created by noel on 2018/6/14.
 */

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    private ArrayList<Live> lives;
    private OnPlayButtonClickListener onPlayButtonClickListener;
    private int lastPosition = -1;
    private Context context;

    public LiveAdapter(Context context) {
        this.context = context;
        lives = new ArrayList<>();
    }

    //--------

    public void setData(ArrayList<Live> lives) {
        this.lives = lives;
        notifyDataSetChanged();
    }

    @Override
    public LiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_youtube_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveAdapter.ViewHolder holder, int position) {
        holder.frameLayout.setBackgroundResource(R.drawable.shape_player_cover_bg);
        holder.imageView.setBackgroundResource(R.drawable.layer_list_play);
        setAnimation(holder.frameLayout, position);
    }


    //-------

    /**
     *設置動畫
     */
    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setDuration(600);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }


    //-----------

    @Override
    public int getItemCount() {
        return lives.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.frame_layout_youtube)
        FrameLayout frameLayout;
        @BindView(R.id.image_view)
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //動態生成ID
            frameLayout.setId(generateViewId());
            imageView.setOnClickListener(this);
        }

        //------------

        @Override
        public void onClick(View view) {
            onPlayButtonClickListener.onPlayButtonClicked(frameLayout, getLayoutPosition());
        }
    }

    //--------------------------------------------------

    /**
     * 監聽Item的ClickListener
     */
    public interface OnPlayButtonClickListener {
        void onPlayButtonClicked(View view, int position);
    }
    //--------------------------------------------------

    /**
     * 設定RecyclerView的onItemClickListener
     *
     * @param onPlayButtonClickListener
     */
    public void setOnPlayButtonClickListener(OnPlayButtonClickListener onPlayButtonClickListener) {
        this.onPlayButtonClickListener = onPlayButtonClickListener;
    }
}
