package com.thesis.brown.brown;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> mItemName;
    private List<Integer> mItemPic;

    public RecyclerAdapter(List<String> itemList, List<Integer> itemPic) {
        mItemName = itemList;
        mItemPic = itemPic;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return RecyclerViewItemHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewItemHolder holder = (RecyclerViewItemHolder) viewHolder;
        String itemText = mItemName.get(position);
        holder.setItemText(itemText);
        if(mItemPic != null) {
            int itemPic = mItemPic.get(position);
            holder.setBackgroundImage(itemPic);
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
