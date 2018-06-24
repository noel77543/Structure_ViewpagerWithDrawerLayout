package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.photo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;

/**
 * Created by noel on 2018/6/14.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<String> datas;
    private onItemClickListener mOnItemClickListener;

    public PhotoAdapter() {
        datas = new ArrayList<>();
    }

    //--------

    public void setData(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_view)
        TextView textView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
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
