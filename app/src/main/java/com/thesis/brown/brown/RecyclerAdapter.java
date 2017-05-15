package com.thesis.brown.brown;

import android.support.annotation.IntegerRes;
import android.support.v7.widget.CardView;
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
            holder.setCardBackground(itemPic);
        }
    }

    @Override
    public int getItemCount() {
        return mItemName == null ? 0 : mItemName.size();
    }

    private static class RecyclerViewItemHolder extends RecyclerView.ViewHolder{
        private final TextView mItemTextView;
        private final CardView mCardView;

        RecyclerViewItemHolder(View itemView, TextView itemTextView, CardView cardView) {
            super(itemView);
            mItemTextView = itemTextView;
            mCardView = cardView;
        }

        static RecyclerViewItemHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemName);
            CardView cardView = (CardView) parent.findViewById(R.id.itemCard);
            return new RecyclerViewItemHolder(parent, itemTextView, cardView);
        }

        void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }

        void setCardBackground(int res) { mCardView.setBackgroundResource(res); }
    }
}
