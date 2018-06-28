package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;

import static android.view.View.generateViewId;

/**
 * Created by noel on 2018/6/14.
 */

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    private ArrayList<String> datas;
    private onItemClickListener mOnItemClickListener;

    public LiveAdapter() {
        datas = new ArrayList<>();
    }

    //--------

    public void setData(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public LiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_youtube_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveAdapter.ViewHolder holder, int position) {
        holder.frameLayout.setBackgroundResource(R.drawable.img_youtube);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.frame_layout_youtube)
        FrameLayout frameLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //動態生成ID
            frameLayout.setId(generateViewId());
            frameLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnItemClickListener.onItemClick(view, getLayoutPosition());
        }
    }

    //--------------------------------------------------

    /**
     * 監聽Item的ClickListener
     */
    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }
    //--------------------------------------------------

    /**
     * 設定RecyclerView的onItemClickListener
     *
     * @param listener
     */
    public void setOnItemClickListener(onItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
