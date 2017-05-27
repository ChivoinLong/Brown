package com.thesis.brown.brown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thesis.brown.brown.ProductDetailActivity;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.RecyclerItemOnClickListener;
import com.thesis.brown.brown.my_support.MyVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllFragment extends Fragment implements RecyclerItemOnClickListener.OnItemClickListener {
    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String EXTRA_IMAGE = "com.thesis.brown.extraImage";
    public static final String EXTRA_TITLE = "com.thesis.brown.extraTitle";
    RecyclerView recyclerView;
    ArrayList<String> productNames, productImageURLs;


    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        productNames = new ArrayList<>();
        productImageURLs = new ArrayList<>();
        loadProducts();


        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), recyclerView, this));

        return root;
    }

    private List<Integer> createItemRes(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(R.drawable.caramel_latte);
            list.add(R.drawable.caramel_macchiato);
            list.add(R.drawable.iced_vanilla_latte);
            list.add(R.drawable.iced_mocha);
            list.add(R.drawable.iced_green_tea_latte);
        }
        return list;
    }

    List<String> createItemList(int num){
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num; i++){
            list.add("Caramel Latte");
            list.add("Caramel Macchiato");
            list.add("Iced Vanilla Latte");
            list.add("Iced Mocha");
            list.add("Iced Green Tea Latte");
        }
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);

        switch (position){
            case 0:
                intent.putExtra(EXTRA_IMAGE, R.drawable.caramel_latte);
                break;
            case 1:
                intent.putExtra(EXTRA_IMAGE, R.drawable.caramel_macchiato);
                break;
            case 2:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_vanilla_latte);
                break;
            case 3:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_mocha);
                break;
            case 4:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_green_tea_latte);
                break;
        }

        intent.putExtra(EXTRA_TITLE, ((TextView) view.findViewById(R.id.itemName)).getText());

        startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, int position) {
        loadProducts();
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

    private void prepareListData(String json) {

        // Copy this json and pass into this website jsonlint.com to have an easy look

        try {
            // Our response json start with { which means it is not json array, it is json object
            JSONObject objResponse = new JSONObject(json);

            // We can get value of each object by its key objResponse.getString("success")
            // All response data is string anyway
            if (objResponse.getString("success").equals("true")) {

                // We get value of data object but inside data starts with { again so we purse it with json object again
                JSONObject data = new JSONObject(objResponse.getString("data"));

                // We need categories but inside categories object data starts with [ so we purse it with json array
                JSONArray categories = new JSONArray(data.getString("category"));

                // We got categories we want in JSONArray and we can loop through to get each data
//                Log.d("result", String.valueOf(categories));

                for (int i = 0; i < categories.length(); i++) {

                    if (String.valueOf(categories.getJSONObject(i).getString("name")).equalsIgnoreCase("drinks")) {
                        Log.d("result", String.valueOf(categories.getJSONObject(i).getString("name")));

                        JSONArray subcategories = new JSONArray(categories.getJSONObject(i).getString("subcategory"));

                        for (int j = 0; j < subcategories.length(); j++) {
                            Log.d("result", String.valueOf(subcategories.getJSONObject(j).getString("name")));

                            JSONArray products = new JSONArray(subcategories.getJSONObject(j).getString("products"));
                            for (int k = 0; k < products.length(); k++) {
                                Log.d("result", String.valueOf(products.getJSONObject(k).getString("name")));

                                productNames.add(products.getJSONObject(k).getString("name"));
                                productImageURLs.add(products.getJSONObject(k).getString("img_url"));
                            }
//                            cModel = new CategoryListModel("InnerHeader", subcategories.getJSONObject(j).getString("name"), subcategories.getJSONObject(j).getString("_id"));
//                            listData.add(cModel);
                        }

                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), productNames, productImageURLs, false);
        recyclerView.setAdapter(recyclerAdapter);
//        listView.setAdapter(new CategoryListAdapter(getActivity().getApplicationContext(), listData));
//        Log.d("result", String.valueOf(listData));
    }

    private void loadProducts() {
        volleyGetCategoryList("https://brown-ordering-system.herokuapp.com/api/v1/product/product", new HashMap<String, String>());
    }





}
