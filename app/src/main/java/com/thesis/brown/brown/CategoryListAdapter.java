package com.thesis.brown.brown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thesis.brown.brown.my_support.DatabaseHandler;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {

    static final int CATEGORY = 1, SUBCATEGORY = 2;
    private ArrayList<com.thesis.brown.brown.model.Category> mCategories;
    private Context context;
    private LayoutInflater inflater;

    public CategoryListAdapter(Context context, ArrayList<com.thesis.brown.brown.model.Category> categories) {
        this.context = context;
        this.mCategories = categories;
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
//        if (mCategories.get(position).getType().equals("category")) {
            return CATEGORY;
//        }
//        return SUBCATEGORY;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int myType = getItemViewType(position);

        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (myType) {
                case CATEGORY:
                    convertView = inflater.inflate(R.layout.list_item_category, null);
                    break;
                case SUBCATEGORY:
                    convertView = inflater.inflate(R.layout.list_item_subcategory, null);
                    break;
            }
        }

        String data = mCategories.get(position).getName();

        switch (myType) {

            case CATEGORY:

                TextView txtHeader = (TextView) convertView.findViewById(R.id.itemName);
                txtHeader.setText(data.toUpperCase());
                break;

            default:

                TextView txtInnerHeader = (TextView) convertView.findViewById(R.id.itemName);
                txtInnerHeader.setText(DatabaseHandler.toDisplayCase(data));
                break;
        }

        return convertView;
    }
}
