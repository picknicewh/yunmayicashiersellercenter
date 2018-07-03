package com.yun.ma.yi.app.module.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoreInfoAdapter extends RecyclerView.Adapter<MoreInfoAdapter.ViewHolder> {

    private List<MoreInfoItem> moreInfoItems;
    private Context context;
    private static OnItemClickListener clickListener;

    public MoreInfoAdapter(Context context, ArrayList<MoreInfoItem> moreInfoItems) {
        this.moreInfoItems = moreInfoItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MoreInfoItem moreInfoItem = moreInfoItems.get(position);
        if (moreInfoItem != null){
            Glide.with(context).load(moreInfoItem.getImageId()).crossFade().into(holder.navIcon);
            holder.navText.setText(moreInfoItem.getText() == null ? "" : moreInfoItem.getText());
        }
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemCount() {
        return moreInfoItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.nav_icon)
        ImageView navIcon;
        @BindView(R.id.nav_text)
        TextView navText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onClick(view,getAdapterPosition());
            }
        }
    }

    /**
     * 设置item 事件监听
     * @param clickListener
     */
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
