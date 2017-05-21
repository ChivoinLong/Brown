package com.thesis.brown.brown;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {

    static final int ROW = 0, HEADER = 1, INNER_HEADER = 2;
    ArrayList<CategoryListModel> myDB;
    Context context;
    LayoutInflater inflater;

    public CategoryListAdapter(Context context, ArrayList<CategoryListModel> myDB) {
        this.context = context;
        this.myDB = myDB;
    }

    @Override
    public int getCount() {
        return myDB.size();
    }

    @Override
    public Object getItem(int position) {
        return myDB.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (myDB.get(position).getType().equals("MainHeader")) {
            return HEADER;
        }
        return INNER_HEADER;
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
                case HEADER:
                    convertView = inflater.inflate(R.layout.item_category_header, null);
                    break;
                case INNER_HEADER:
                    convertView = inflater.inflate(R.layout.item_category_inner_header, null);
                    break;
            }
        }

        String data = myDB.get(position).getName();

        switch (myType) {

            case HEADER:

                TextView txtHeader = (TextView) convertView.findViewById(R.id.txtHeaderCateHeader);
                txtHeader.setText(data);
                break;

            default:

                TextView txtInnerHeader = (TextView) convertView.findViewById(R.id.txtInnerHeaderCateHeader);
                txtInnerHeader.setText(data);
                break;
        }

        return convertView;
    }
}
