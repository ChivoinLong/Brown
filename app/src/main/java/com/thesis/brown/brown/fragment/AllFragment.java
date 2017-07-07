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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thesis.brown.brown.ProductDetailActivity;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.RecyclerItemOnClickListener;
import com.thesis.brown.brown.model.Product;
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

public class AllFragment extends Fragment implements RecyclerItemOnClickListener.OnItemClickListener {
    public static final String TAB_NAME = "ALL";
    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String EXTRA_ID = "com.thesis.brown.extraID";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button btnRefresh;
    ArrayList<Product> products = null;
    DatabaseHandler db;

    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        db = new DatabaseHandler(getActivity().getApplicationContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        btnRefresh = (Button) root.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProducts();
                progressBar.setVisibility(View.VISIBLE);
                btnRefresh.setVisibility(View.GONE);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), recyclerView, this));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (db.getProductCount() == 0) {
            loadProducts();
//        } else {
//            products = db.getAllProducts();
//            Log.d("123", "onResume: " + products.size());
//            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), this.products, false);
//            recyclerView.setAdapter(recyclerAdapter);
//            progressBar.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra(EXTRA_ID, products.get(position).get_id());
//        intent.putExtra(EXTRA_TRANSITION_NAME, products.get(position).get_name());
//
//        ImageView itemIv = (ImageView) view.findViewById(R.id.itemImage);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), itemIv, products.get(position).get_name());

        startActivity(intent/*, options.toBundle()*/);
    }

    @Override
    public void onLongItemClick(View view, int position) {
    }


    private void volleyGetProductList(String url, final Map<String, String> params) {

        MyVolley.cancelOldPandingRequest();

        StringRequest stringRequest = new StringRequest(
                Request.Method.DEPRECATED_GET_OR_POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prepareListData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btnRefresh.setVisibility(View.VISIBLE);
                    }
                }
        )
        {
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

        products = new ArrayList<>();
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

                    JSONArray subcategories = new JSONArray(categories.getJSONObject(i).getString("subcategory"));

                    if (subcategories.length() > 0) {

                        for (int j = 0; j < subcategories.length(); j++) {

                            JSONArray products = new JSONArray(subcategories.getJSONObject(j).getString("products"));
                            for (int k = 0; k < products.length(); k++) {
                                Log.d("result", String.valueOf(products.getJSONObject(k).getString("name")));

                                Product product = new Product();
                                product.set_id(products.getJSONObject(k).getString("_id"));
                                product.set_name(products.getJSONObject(k).getString("name"));
                                product.set_imgUrl(products.getJSONObject(k).getString("img_url"));
                                this.products.add(product);
                                db.addProduct(product);
                            }
                        }

                    } else {
                        JSONArray products = new JSONArray(categories.getJSONObject(i).getString("products"));
                        for (int k = 0; k < products.length(); k++) {
                            Log.d("result", String.valueOf(products.getJSONObject(k).getString("name")));

                            Product product = new Product();
                            product.set_id(products.getJSONObject(k).getString("_id"));
                            product.set_name(products.getJSONObject(k).getString("name"));
                            product.set_imgUrl(products.getJSONObject(k).getString("img_url"));
                            this.products.add(product);
                            db.addProduct(product);
                        }
                    }
                }

            }
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), this.products, false);
            recyclerView.setAdapter(recyclerAdapter);
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        listView.setAdapter(new CategoryListAdapter(getActivity().getApplicationContext(), allCategories));
//        Log.d("result", String.valueOf(allCategories));
    }

    private void loadProducts() {
        volleyGetProductList("https://brown-ordering-system.herokuapp.com/api/v1/product/product", new HashMap<String, String>());
    }





}
