package com.thesis.brown.brown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.thesis.brown.brown.CategoryListAdapter;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.my_support.MySupporter;
import com.thesis.brown.brown.my_support.MySupporter_Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryFragment extends Fragment implements MySupporter_Interface {

    public static final String POSITION_KEY = "FragmentPositionKey";
    public static boolean gettingFirstData = true;
    public CategoryFragment context;
    ListView listView;
    ArrayList<Object> listData;
    ArrayList<Object> listJsonDB;
    ProgressBar proLoading;


    public static CategoryFragment newInstance(Bundle args) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void GetFirstData() {
        if (gettingFirstData) {
            MySupporter.Volley("https://brown-ordering-system.herokuapp.com/api/v1/product", new HashMap<String, String>(), context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        listView = (ListView) root.findViewById(R.id.listview);
        proLoading = (ProgressBar) root.findViewById(R.id.proLoadingMainCategory);
        listData = new ArrayList<>();
        listJsonDB = new ArrayList<>();
        context = this;

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setEvents();
        startUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        gettingFirstData = true;

    }

    private void setEvents() {

    }

    void startUp() {
        GetFirstData();
    }

    private void prepareListData(String json) {
        CategoryListAdapter.CategoryListModel model;
        CategoryListAdapter.CategoryListModelInnerSub sub;
        listView.setVisibility(View.VISIBLE);
        proLoading.setVisibility(View.GONE);

        try {
            JSONArray data = new JSONArray(new JSONObject(new JSONObject(json).getString("data")).getString("category"));

            for (int i = 0; i < data.length(); i++) {
                Log.d("result", data.getJSONObject(i).getString("name"));
                listData.add(data.getJSONObject(i).getString("name"));

                if (new JSONArray(data.getJSONObject(i).getString("products")).length() < 1) {

                    for (int j = 0; j < new JSONArray(data.getJSONObject(i).getString("subcategory")).length(); j++) {
                        JSONArray subcategory = new JSONArray(new JSONObject(String.valueOf(new JSONArray(data.getJSONObject(i).getString("subcategory")).getJSONObject(j))).getString("products"));

                        sub = new CategoryListAdapter.CategoryListModelInnerSub(new JSONObject(String.valueOf(new JSONArray(data.getJSONObject(i).getString("subcategory")).getJSONObject(j))).getString("name"));
                        listData.add(sub);

                        for (int k = 0; k < subcategory.length(); k++) {
                            model = new CategoryListAdapter.CategoryListModel(subcategory.getJSONObject(k).getString("name"));
                            listData.add(model);
                        }
                    }
                } else {
                    for (int j = 0; j < new JSONArray(data.getJSONObject(i).getString("products")).length(); j++) {
                        model = new CategoryListAdapter.CategoryListModel((new JSONArray(data.getJSONObject(i).getString("products")).getJSONObject(j).getString("name")));
                        listData.add(model);
                    }
                }

            }

            Log.d("result", String.valueOf(listData));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setAdapter(new CategoryListAdapter(getActivity().getApplicationContext(), listData));
    }

    @Override
    public void onVolleyFinished(String response) {
        gettingFirstData = false;

        try {
            if (String.valueOf(new JSONObject(response).getString("status")).equals("200")) {
                prepareListData(response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onVolleyError(String message) {
        GetFirstData();
    }
}
