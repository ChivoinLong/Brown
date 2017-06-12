package com.thesis.brown.brown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thesis.brown.brown.model.Category;
import com.thesis.brown.brown.my_support.DatabaseHandler;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {

    static final int ROW = 0, CATEGORY = 1, SUBCATEGORY = 2;
    ArrayList<Category> myCategory;
    Context context;
    LayoutInflater inflater;

    public CategoryListAdapter(Context context, ArrayList<Category> myDB) {
        this.context = context;
        this.myCategory = myDB;
    }

    @Override
    public int getCount() {
        return myCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return myCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (myCategory.get(position).getType().equals("category")) {
            return CATEGORY;
        }
        return SUBCATEGORY;
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
                    convertView = inflater.inflate(R.layout.item_category_header, null);
                    break;
                case SUBCATEGORY:
                    convertView = inflater.inflate(R.layout.item_category, null);
                    break;
            }
        }

        String data = myCategory.get(position).getName();

        switch (myType) {

            case CATEGORY:

                TextView txtHeader = (TextView) convertView.findViewById(R.id.txtHeaderCateHeader);
                txtHeader.setText(data.toUpperCase());
                break;

            default:

                TextView txtInnerHeader = (TextView) convertView.findViewById(R.id.txtCategoryName);
                txtInnerHeader.setText(DatabaseHandler.toDisplayCase(data));
                break;
        }

        return convertView;
    }
}
