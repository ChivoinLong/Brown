package com.thesis.brown.brown.Category;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thesis.brown.brown.R;

import java.util.ArrayList;

/**
 * Created by lolzzlolzz on 7/16/16.
 */
public class CateListAdp extends BaseAdapter {

    ArrayList<Object> myDB;
    Context context;
    LayoutInflater inflater;
    static final int ROW = 0;
    static final int HEADER = 1;

    public CateListAdp(Context context, ArrayList<Object> myDB){
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

        if (getItem(position) instanceof CateListModel){
            return ROW;
        }
        return HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int myType = getItemViewType(position);

        if (convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (myType){
                case ROW :
                    convertView = inflater.inflate(R.layout.item_category,null);
                    break;
                default:
                    convertView = inflater.inflate(R.layout.item_category_header,null);
                    break;
            }
        }

        switch (myType){

            case ROW :
                CateListModel phoneListModel = (CateListModel)getItem(position);

                TextView txtName = (TextView)convertView.findViewById(R.id.txtNameCateRow);

                txtName.setText(phoneListModel.getName());

                break;
            default:
                String header = (String)getItem(position);

                TextView txtHeader = (TextView)convertView.findViewById(R.id.txtHeaderCateHeader);

                txtHeader.setText(header);

                break;
        }


        return convertView;
    }









}
