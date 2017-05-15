package com.thesis.brown.brown.MySupport;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lolzzlolzz on 5/15/17.
 */

public class MySupporter {

    static MySupporter_Interface mySupporter_interface;

    public static void Volley (String url, final Map<String, String> params, Context _context){

        MyVolley.cancelOldPandingRequest();
        mySupporter_interface = (MySupporter_Interface) _context;

        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mySupporter_interface.onVolleyFinished(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mySupporter_interface.onVolleyError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

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
