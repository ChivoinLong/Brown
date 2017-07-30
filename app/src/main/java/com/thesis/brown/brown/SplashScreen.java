package com.thesis.brown.brown;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thesis.brown.brown.my_support.MyVolley;
import com.thesis.brown.brown.realm_model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;

/**
 * Created by Obi-Voin Kenobi on 16-Jul-17.
 */

public class SplashScreen extends AppCompatActivity {

    private Realm realm;
    private boolean isMsgShown;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        
        realm = Realm.getDefaultInstance();
        loadDataFromWebService();
        Log.e("TAG", "onCreate: Splash Screen");
    }

    private void loadDataFromWebService() {
        if (realm.isEmpty()){
            Log.e("AllFragment", "onResume: get data from web service!");
            volleyGetProductList("https://brown-ordering-system.herokuapp.com/api/v1/product/product", new HashMap<String, String>());
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void volleyGetProductList(String url, final HashMap<String, String> params) {
        MyVolley.cancelOldPandingRequest();

        StringRequest stringRequest = new StringRequest(
                Request.Method.DEPRECATED_GET_OR_POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prepareData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (!isMsgShown) {
                            Toast.makeText(SplashScreen.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                            isMsgShown = true;
                        }
                        loadDataFromWebService();
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

    private void prepareData(String json) {
        try {
            JSONObject objResponse = new JSONObject(json);

            if (objResponse.getString("success").equals("true")) {

                JSONObject data = new JSONObject(objResponse.getString("data"));
                final JSONArray categories = new JSONArray(data.getString("category"));

                //Write into Realm DB
                realm.executeTransactionAsync(
                        new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.createOrUpdateAllFromJson(Category.class, categories);
                            }
                        },
                        new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                finish();
                            }
                        }
                );
            }
            else {
                Toast.makeText(SplashScreen.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                loadDataFromWebService();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SplashScreen.this, "Something went wrong!\nPlease contact the developers.", Toast.LENGTH_LONG).show();
        }
    }
}
