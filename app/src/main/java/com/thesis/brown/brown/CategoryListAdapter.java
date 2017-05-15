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
    ArrayList<Object> myDB;
    Context context;
    LayoutInflater inflater;

    public CategoryListAdapter(Context context, ArrayList<Object> myDB) {
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
        if (getItem(position) instanceof CategoryListModel) {
            return ROW;
        } else if (getItem(position) instanceof CategoryListModelInnerSub) {
            return INNER_HEADER;
        }
        return HEADER;
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
                case ROW:
                    convertView = inflater.inflate(R.layout.item_category, null);
                    break;
                case INNER_HEADER:
                    convertView = inflater.inflate(R.layout.item_category_inner_header, null);
                    break;
                default:
                    convertView = inflater.inflate(R.layout.item_category_header, null);
                    break;
            }
        }

        switch (myType) {
            case ROW:
                CategoryListModel categoryListModel = (CategoryListModel) getItem(position);
                TextView txtName = (TextView) convertView.findViewById(R.id.txtNameCateRow);
                txtName.setText(categoryListModel.getName());
                convertView.setBackgroundColor(Color.parseColor("#FFFDFDFD"));
                break;

            case INNER_HEADER:
                CategoryListModelInnerSub categoryListModelInnerSub = (CategoryListModelInnerSub) getItem(position);
                TextView txtInnerHeader = (TextView) convertView.findViewById(R.id.txtInnerHeaderCateHeader);
                txtInnerHeader.setText(categoryListModelInnerSub.getName());
                break;

            default:
                String header = (String) getItem(position);
                TextView txtHeader = (TextView) convertView.findViewById(R.id.txtHeaderCateHeader);
                txtHeader.setText(header);
                convertView.setBackgroundColor(Color.parseColor("#FFE4E4E4"));
                break;
        }

        return convertView;
    }


    public static class CategoryListHolder {
        TextView txtHeader, txtName;
    }

    public static class CategoryListModel {
        private String name;

        public CategoryListModel(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class CategoryListModelInnerSub {
        private String title;

        public CategoryListModelInnerSub(String title) {
            this.title = title;
        }

        public String getName() {
            return title;
        }
    }
}
