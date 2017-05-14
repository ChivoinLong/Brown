package com.thesis.brown.brown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.thesis.brown.brown.StoreList.ListStoreListModel;

import org.json.JSONException;
import org.json.JSONObject;

public class MainShowDetailStore extends AppCompatActivity {

    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_detail_store);

        try {
            data = new JSONObject(getIntent().getStringExtra("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, String.valueOf(data), Toast.LENGTH_LONG).show();
    }
}
