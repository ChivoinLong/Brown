package com.thesis.brown.brown.my_support;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyVolley extends Application {

    static final String TAG = "DEFAULT";
    static MyVolley myInstance;
    static RequestQueue myRequestQ;
    static String OLDTAG = "DEFAULT";

    public static synchronized MyVolley getMyInstance() {
        return myInstance;
    }

    public static void cancelPendingRequests(Object tag) {
        if (myRequestQ != null) {
            myRequestQ.cancelAll(tag);
        }
    }

    public static void cancelOldPandingRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MyVolley.getMyInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
        myInstance = this;
    }

    public RequestQueue getMyRequestQ(){
        if (myRequestQ == null){
            myRequestQ = Volley.newRequestQueue(this.getApplicationContext());
        }
        else{
            cancelPendingRequests(OLDTAG);
        }
        OLDTAG = TAG;
        return myRequestQ;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(TextUtils.isEmpty(tag)? TAG : tag);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getMyRequestQ().add(request);
    }
}
