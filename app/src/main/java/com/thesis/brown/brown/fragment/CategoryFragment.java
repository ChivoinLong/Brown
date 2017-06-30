package com.thesis.brown.brown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thesis.brown.brown.CategoryListAdapter;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.SubcategoryActivity;
import com.thesis.brown.brown.model.Category;
import com.thesis.brown.brown.my_support.DatabaseHandler;
import com.thesis.brown.brown.my_support.MyVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryFragment extends Fragment{

    public static final String TAB_NAME = "CATEGORIES";
    public static final String POSITION_KEY = "FragmentPositionKey";
    private final String TAG = "DEBUGGGGGGGG";
    public CategoryFragment context;
    ListView listView;
    ArrayList<Category> allCategories = null;
    ProgressBar proLoading;
    DatabaseHandler db;


    public static CategoryFragment newInstance(Bundle args) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        listView = (ListView) root.findViewById(R.id.listview);
        proLoading = (ProgressBar) root.findViewById(R.id.proLoadingMainCategory);
        context = this;
        db = new DatabaseHandler(getContext());

//        if(allCategories == null) {
//            if(db.getCategoryCount(null) == 0){
//                startUp();
//                Log.d("TAG", "onCreateView: get from internet");
//            }
//            else {
//                allCategories = db.getAllCategories();
//                listView.setAdapter(new CategoryListAdapter(getContext(), allCategories));
//                proLoading.setVisibility(View.GONE);
//                Log.d("TAG", "onCreateView: get from db");
//            }
//        }
//        else {
//            listView.setAdapter(new CategoryListAdapter(getActivity().getApplicationContext(), allCategories));
//            proLoading.setVisibility(View.GONE);
//            Log.d("TAG", "onCreateView: get from var");
//        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (db.getCategoryCount(null) == 0) {
            startUp();
            Log.d("TAG", "onCreateView: get from internet");
        } else {
            allCategories = db.getAllCategories();
            Log.d(TAG, "onResume: " + allCategories.size());
            listView.setAdapter(new CategoryListAdapter(getActivity().getApplicationContext(), allCategories));
            proLoading.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            Log.d("TAG", "onCreateView: get from db");
        }
        setEvents();
//        startUp();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("click", "\n Type : " + allCategories.get(i).getType() + "\n Name : " + allCategories.get(i).getName() + "\n ID : " + allCategories.get(i).getId());
                // We can understand what we are gonna get detail whether cate or sub-cate
                Intent intent = new Intent(getActivity().getApplicationContext(), SubcategoryActivity.class);
                intent.putExtra("CATEGORY_ID", allCategories.get(i).getId());
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), allCategories.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void startUp() {
        volleyGetCategoryList("https://brown-ordering-system.herokuapp.com/api/v1/product/categorylist", new HashMap<String, String>());
    }

    private void prepareListData(String json) {
        listView.setVisibility(View.VISIBLE);
        proLoading.setVisibility(View.GONE);
        allCategories = new ArrayList<>();
        Log.d(TAG, "prepareListData: called");

        // Copy this json and pass into this website jsonlint.com to have an easy look

        try {
            // Our response json start with { which means it is not json array, it is json object
            JSONObject objResponse = new JSONObject(json);

            // We can get value of each object by its key objResponse.getString("success")
            // All response data is string anyway
            if (objResponse.getString("success").equals("true")){

                // We get value of data object but inside data starts with { again so we purse it with json object again
                JSONObject data = new JSONObject(objResponse.getString("data"));

                // We need categories but inside categories object data starts with [ so we purse it with json array
                JSONArray categories = new JSONArray(data.getString("categories"));

                // We got categories we want in JSONArray and we can loop through to get each data
                Log.d("result", String.valueOf(categories));

                Category cModel;
                for (int i=0; i<categories.length(); i++){

                    cModel = new Category(categories.getJSONObject(i).getString("_id"), categories.getJSONObject(i).getString("name"), "category", null);
                    allCategories.add(cModel);
                    db.addCategory(cModel);
                    Log.d(TAG, "prepareListData: " + cModel);

                    // Check whether it contains subcategory or not
                    if (categories.getJSONObject(i).getString("subcategory").length() > 0) {

                        // Purse subcategory into json array because it starts with [
                        JSONArray subcategory = new JSONArray(categories.getJSONObject(i).getString("subcategory"));

                        for (int j=0; j<subcategory.length(); j++) {
                            cModel = new Category(subcategory.getJSONObject(j).getString("_id"), subcategory.getJSONObject(j).getString("name"), "subcategory", categories.getJSONObject(i).getString("_id"));
                            allCategories.add(cModel);
                            db.addCategory(cModel);
                            Log.d(TAG, "prepareListData: " + cModel);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setAdapter(new CategoryListAdapter(getContext(), allCategories));
        Log.d("result", String.valueOf(allCategories));
    }

    private void volleyGetCategoryList(String url, final Map<String, String> params) {

        MyVolley.cancelOldPandingRequest();

        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // If we could get data, we pass to another method to be easy to see :D

                prepareListData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // If it has errors with Internet connection or web page
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Here is to Encoder your query string params because we cannot push special characters through URL

                String key = "", value = "";
                HashMap<String, String> maps = new HashMap<>();

                for (Map.Entry<String, String> entry : params.entrySet()) {

                    key = entry.getKey();

                    try {
                        value = URLEncoder.encode(entry.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    maps.put(key, value);
                }

                return maps;
            }
        };

        MyVolley.getMyInstance().addToRequestQueue(stringRequest);
    }

}
