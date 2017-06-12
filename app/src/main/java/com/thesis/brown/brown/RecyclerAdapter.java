package com.thesis.brown.brown;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thesis.brown.brown.model.Product;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<Product> mAllProducts;
    private boolean isGrid = false;

    public RecyclerAdapter(Context mContext, ArrayList<Product> allProducts, boolean isGrid) {
        this.mContext = mContext;
        this.mAllProducts = allProducts;
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

        Product p = mAllProducts.get(position);
        holder.setItemText(p.get_name());
        if (isGrid) {
            Picasso.with(mContext)
                    .load(p.get_imgUrl())
                    .placeholder(R.drawable.progress)
                    .into(holder.mItemImage);
        } else {
            Picasso.with(mContext)
                    .load(p.get_imgUrl())
                    .placeholder(R.drawable.progress)
                    .transform(new RoundImageTransform())
                    .into(holder.mItemImage);
        }
        ViewCompat.setTransitionName(holder.mItemImage, p.get_name());
    }

    @Override
    public int getItemCount() {
        return mAllProducts == null ? 0 : mAllProducts.size();
    }

    private static class RecyclerViewItemHolder extends RecyclerView.ViewHolder {
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
    }
}
