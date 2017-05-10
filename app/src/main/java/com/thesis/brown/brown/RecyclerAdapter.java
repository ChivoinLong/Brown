package com.thesis.brown.brown;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Obi-Voin Kenobi on 26-Mar-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_grid, parent, false);
        return RecyclerViewItemHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewItemHolder holder = (RecyclerViewItemHolder) viewHolder;
        String itemText = mItemList.get(position);
        holder.setItemText(itemText);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    private static class RecyclerViewItemHolder extends RecyclerView.ViewHolder{
        private final TextView mItemTextView;

        RecyclerViewItemHolder(View itemView, TextView itemTextView) {
            super(itemView);
            mItemTextView = itemTextView;
        }

        static RecyclerViewItemHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
            return new RecyclerViewItemHolder(parent, itemTextView);
        }

        void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }
    }
}
