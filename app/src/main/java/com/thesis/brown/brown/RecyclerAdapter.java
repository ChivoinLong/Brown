package com.thesis.brown.brown;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thesis.brown.brown.realm_model.Category;
import com.thesis.brown.brown.realm_model.OrderedProduct;
import com.thesis.brown.brown.realm_model.Product;

import io.realm.Case;
import io.realm.RealmResults;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int LINEAR_PRODUCT = 1;
    private static final int GRID_PRODUCT = 2;
    private static final int CATEGORY = 3;
    private static final int SUBCATEGORY = 4;
    private static final int ORDERED_PRODUCT = 5;

    private Context mContext;
    private int mViewType = -1;
    private boolean mIsGrid = false;
    private RealmResults<Product> mAllProducts;
    private RealmResults<Category> mAllCategories;
    private RealmResults<OrderedProduct> mOrderedProducts;

    public RecyclerAdapter(Context context, RealmResults<Product> products, int viewType, boolean isGrid) {
        mContext = context;
        mAllProducts = products;
        mViewType = viewType;
        mIsGrid = isGrid;
    }

    public RecyclerAdapter(Context context, RealmResults<Category> allCategories, int viewType) {
        mContext = context;
        mAllCategories = allCategories;
        mViewType = viewType;
    }

    public RecyclerAdapter(Context context, RealmResults<Product> products, RealmResults<OrderedProduct> orderedProducts, int viewType) {
        mContext = context;
        mAllProducts = products;
        mOrderedProducts = orderedProducts;
        mViewType = viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType){
            case LINEAR_PRODUCT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_list, parent, false);
                return ProductItemHolder.newInstance(view);
            case GRID_PRODUCT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_square, parent, false);
                return ProductItemHolder.newInstance(view);
            case CATEGORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
                return CategoryItemHolder.newInstance(view);
            case ORDERED_PRODUCT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
                return OrderedProductItemHolder.newInstance(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = viewHolder.getItemViewType();
        if (viewType == GRID_PRODUCT || viewType == LINEAR_PRODUCT) {
            ProductItemHolder holder = (ProductItemHolder) viewHolder;

            Product p = mAllProducts.get(position);
            holder.setItemText(p.getName());
            Picasso.with(mContext)
                    .load(p.getImg_url())
                    .placeholder(R.drawable.progress)
                    .into(holder.mItemImage);
        }
        else if (viewType == CATEGORY){
            CategoryItemHolder holder = (CategoryItemHolder) viewHolder;

            Category c = mAllCategories.get(position);
            holder.setItemText(c.getName());
        }
        else if (viewType == ORDERED_PRODUCT) {
            OrderedProductItemHolder holder = (OrderedProductItemHolder) viewHolder;

            OrderedProduct op = mOrderedProducts.get(position);
            Product p = mAllProducts.where().equalTo("_id", op.getId(), Case.INSENSITIVE).findFirst();
            holder.setItemName(p.getName());
            holder.setItemUnitPrice(p.getPrice().where().equalTo("name", op.getSize(), Case.INSENSITIVE).findFirst().getAmount().toString());
            holder.setItemQuantity(op.getQuantity().toString());

            Picasso.with(mContext)
                    .load(p.getImg_url())
                    .placeholder(R.drawable.progress)
                    .into(holder.mItemImage);
        }
    }

    @Override
    public int getItemCount() {
        if (mViewType == LINEAR_PRODUCT || mViewType == GRID_PRODUCT)
            return mAllProducts.size();
        else if (mViewType == CATEGORY)
            return mAllCategories.size();
        else if (mViewType == ORDERED_PRODUCT)
            return mOrderedProducts.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    private static class ProductItemHolder extends RecyclerView.ViewHolder {
        private final TextView mItemTextView;
        private final ImageView mItemImage;

        ProductItemHolder(View itemView, TextView itemTextView, ImageView itemImage) {
            super(itemView);
            mItemTextView = itemTextView;
            mItemImage = itemImage;
        }

        static ProductItemHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemName);
            ImageView itemImageView = (ImageView) parent.findViewById(R.id.itemImage);
            return new ProductItemHolder(parent, itemTextView, itemImageView);
        }

        void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }
    }

    private static class CategoryItemHolder extends RecyclerView.ViewHolder {
        private final TextView mItemTextView;

        CategoryItemHolder(View itemView, TextView itemTextView) {
            super(itemView);
            mItemTextView = itemTextView;
        }

        static CategoryItemHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemName);
            return new CategoryItemHolder(parent, itemTextView);
        }

        void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }
    }

    private static class OrderedProductItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemName;
        private ImageView mItemImage;
        private TextView mUnitPrice;
        private TextView mQuantity;

        OrderedProductItemHolder(View itemView, TextView itemName, ImageView itemImage, TextView unitPrice, TextView quantity) {
            super(itemView);
            mItemName = itemName;
            mItemImage = itemImage;
            mUnitPrice = unitPrice;
            mQuantity = quantity;
        }

        static OrderedProductItemHolder newInstance(View parent) {
            TextView itemName = (TextView) parent.findViewById(R.id.itemName);
            ImageView itemImage = (ImageView) parent.findViewById(R.id.itemImage);
            TextView unitPrice = (TextView) parent.findViewById(R.id.itemUnitPrice);
            TextView quantity = (TextView) parent.findViewById(R.id.itemQuantity);

            return new OrderedProductItemHolder(parent, itemName, itemImage, unitPrice, quantity);
        }

        void setItemName(CharSequence text) {
            mItemName.setText(text);
        }

        public void setItemUnitPrice(CharSequence text) {
            mUnitPrice.setText(text);
        }

        public void setItemQuantity(CharSequence text) {
            mQuantity.setText(text);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
