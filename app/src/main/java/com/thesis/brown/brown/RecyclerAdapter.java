package com.thesis.brown.brown;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<String> mItemName;
    private List<Integer> mItemPic;
    private boolean isGrid = false;

    public RecyclerAdapter(Context context, List<String> itemList, List<Integer> itemPic, boolean isGrid) {
        mContext = context;
        mItemName = itemList;
        mItemPic = itemPic;
        this.isGrid = isGrid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (isGrid) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_square, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_list, parent, false);
        }

        return RecyclerViewItemHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewItemHolder holder = (RecyclerViewItemHolder) viewHolder;
        String itemText = mItemName.get(position);
        int itemPic = mItemPic.get(position);

        holder.setItemText(itemText);
        if (isGrid) {
            holder.setBackgroundImage(itemPic);
        } else {
            Picasso.with(mContext)
                    .load(itemPic)
                    .transform(new RoundImageTransform())
                    .into(holder.mItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return mItemName == null ? 0 : mItemName.size();
    }

    private static class RecyclerViewItemHolder extends RecyclerView.ViewHolder{
        private final TextView mItemTextView;
        private final ImageView mItemImage;

        RecyclerViewItemHolder(View itemView, TextView itemTextView, ImageView itemImage) {
            super(itemView);
            mItemTextView = itemTextView;
            mItemImage = itemImage;
        }

        static RecyclerViewItemHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemName);
            ImageView itemImageView = (ImageView) parent.findViewById(R.id.itemImage);
            return new RecyclerViewItemHolder(parent, itemTextView, itemImageView);
        }

        void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }

        void setBackgroundImage(int res) {
            mItemImage.setImageResource(res);
        }
    }
}
