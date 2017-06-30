package com.thesis.brown.brown.store_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thesis.brown.brown.MainShowDetailStore;
import com.thesis.brown.brown.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainListStore extends Fragment {

    ListView lisStoreList;
    ListStoreListAdp adp;

    //set the models ArrayList to be data ready variable.
    ArrayList<ListStoreListModel> models = getListData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_list_store, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lisStoreList = (ListView) getActivity().findViewById(R.id.lisStoreList);


        adp = new ListStoreListAdp(getActivity(), R.layout.lis_store_list, models);
        lisStoreList.setAdapter(adp);

        lisStoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MainShowDetailStore.class);

                JSONObject object = new JSONObject();
                try {
                    object.put("title", models.get(i).getTitle());
                    object.put("road", models.get(i).getRoad());
                    object.put("phone", models.get(i).getPhone());
                    object.put("link", models.get(i).getLink());
                    object.put("img", models.get(i).getImage());
                    object.put("opening", models.get(i).getOpeningTime());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("data", String.valueOf(object));
                startActivity(intent);
            }
        });
    }


    //work : Return list of stores
    ArrayList<ListStoreListModel> getListData (){
        ArrayList<ListStoreListModel> models = new ArrayList<>();

        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        models.add(new ListStoreListModel("Brown Pencil","#17, St. 214 (Near Pencil Supermarket and ICS), Boeng Raing, Khan Daun Penh, Phnom Penh, Kingdom of Cambodia", "069 697 079", "www.facebook.com/browncoffee.kh" ,"Everyday  06:30 am - 08:00 pm",R.drawable.brown_pencil,11.561341,104.925884));
        return models;
    }


}
