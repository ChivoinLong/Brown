package com.thesis.brown.brown.store_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thesis.brown.brown.R;

import java.util.ArrayList;

public class ListStoreListAdp extends ArrayAdapter<ListStoreListModel> {

    private Context context;
    private int resource;
    private LayoutInflater layoutInflater;

    public ListStoreListAdp(Context _con, int _res, ArrayList<ListStoreListModel> listModels){

        super(_con,_res,listModels);

        context = _con;
        resource = _res;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListStoreListHolder listStoreListHolder;

        if (convertView == null){

            convertView = layoutInflater.inflate(this.resource,null);
            listStoreListHolder = new ListStoreListHolder();
            listStoreListHolder.imgLocation = (ImageView) convertView.findViewById(R.id.imgLocationStoreList);
            listStoreListHolder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitleStoreList);
            listStoreListHolder.txtRoad = (TextView)convertView.findViewById(R.id.txtRoadStoreList);
            convertView.setTag(listStoreListHolder);

        }
        else {
            listStoreListHolder = (ListStoreListHolder)convertView.getTag();
        }

        ListStoreListModel listStoreListModel = getItem(position);

        listStoreListHolder.imgLocation.setImageResource(listStoreListModel.getImage());
        listStoreListHolder.txtTitle.setText(listStoreListModel.getTitle());
        listStoreListHolder.txtRoad.setText(listStoreListModel.getRoad());
//        Picasso.with(context)
//                .load(listStoreListModel.getImage())
//                .placeholder(R.drawable.progress)
//                .into(listStoreListHolder.imgLocation);

        return convertView;
    }

}
