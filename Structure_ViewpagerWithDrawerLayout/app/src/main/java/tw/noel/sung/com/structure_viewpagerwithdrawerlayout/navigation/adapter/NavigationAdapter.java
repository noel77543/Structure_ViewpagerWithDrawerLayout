package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.noel.sung.com.structure_viewpagerwithdrawerlayout.R;

/**
 * Created by noel on 2018/6/9.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] items;
    private onItemClickListener mOnItemClickListener;

    //---------
    public NavigationAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        items = new String[0];
    }

    //------------

    /***
     *  放入資料
     * @param items
     */
    public void setData(String[] items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //--------
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_navigation, null);
        return new ViewHolder(view);
    }
    //------------------------

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textViewItem.setText(items[position]);
    }

    //------------------------

    @Override
    public int getItemCount() {
        return items.length;
    }


    //-----------------------
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.text_view_item)
        TextView textViewItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }
        //-----------

        /***
         *    當點選項目
         * @param view
         */
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
