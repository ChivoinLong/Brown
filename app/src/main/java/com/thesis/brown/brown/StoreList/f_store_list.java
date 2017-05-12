package com.thesis.brown.brown.StoreList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thesis.brown.brown.MainShowDetailStore;
import com.thesis.brown.brown.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class f_store_list extends AppCompatActivity {

    ListView lisStoreList;
    ListStoreListAdp adp;
    ArrayList<ListStoreListModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_store_list);

        lisStoreList = (ListView)findViewById(R.id.lisStoreList);

        getListData();
        adp = new ListStoreListAdp(this, R.layout.lis_store_list, models);
        lisStoreList.setAdapter(adp);

        lisStoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), MainShowDetailStore.class);

                JSONObject object = new JSONObject();
                try {
                    object.put("title", models.get(i).getTitle());
                    object.put("road", models.get(i).getRoad());
                    object.put("phone", models.get(i).getPhone());
                    object.put("link", models.get(i).getLink());
                    object.put("img", models.get(i).getImgURL());
                    object.put("opening", models.get(i).getOpeningTime());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("data", String.valueOf(object));
                startActivity(intent);
            }
        });
    }

    void getListData (){
        models = new ArrayList<>();

        models.add(new ListStoreListModel("Brown 4 (Riverside)", "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside).", "(855) 10 917 907", "www.facebook.com/browncoffee.kh", "imgURL", "06:30 AM - 09:00 PM"));
        models.add(new ListStoreListModel("Brown 4 (Riverside)", "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside).", "(855) 10 917 907", "www.facebook.com/browncoffee.kh", "imgURL", "06:30 AM - 09:00 PM"));
        models.add(new ListStoreListModel("Brown 4 (Riverside)", "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside).", "(855) 10 917 907", "www.facebook.com/browncoffee.kh", "imgURL", "06:30 AM - 09:00 PM"));
        models.add(new ListStoreListModel("Brown 4 (Riverside)", "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside).", "(855) 10 917 907", "www.facebook.com/browncoffee.kh", "imgURL", "06:30 AM - 09:00 PM"));
        models.add(new ListStoreListModel("Brown 4 (Riverside)", "#1 st. 98 corner Sisowath Quay (adjacent to KFC Riverside).", "(855) 10 917 907", "www.facebook.com/browncoffee.kh", "imgURL", "06:30 AM - 09:00 PM"));
    }
}
