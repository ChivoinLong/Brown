package com.thesis.brown.brown.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thesis.brown.brown.MySupport.MySupporter;
import com.thesis.brown.brown.MySupport.MySupporter_Interface;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment implements MySupporter_Interface{

    ListView lisCate;
    ArrayList<Object> lisData;
    ArrayList<Object> lisJsonDB;
    CateListAdp adp;
    public static boolean gettingFirstData = true;

    public static final String POSITION_KEY = "FragmentPositionKey";

    public static CategoryFragment newInstance(Bundle args) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setControls();
        setEvents();
        startUp();
    }

    void setControls (){
        lisCate = (ListView)getActivity().findViewById(R.id.lisCategoryFragment);
    }

    void setEvents (){

    }

    void startUp (){
        lisData = new ArrayList<>();
        lisJsonDB = new ArrayList<>();
        showListWithData();
    }

    public static void GetFirstData(){
        if (gettingFirstData){
//            MySupporter.Volley("https://brown-ordering-system.herokuapp.com/api/v1/product");
        }
    }

    void showListWithData (){

        CateListModel model;
        CateListModelInnerSub sub;

        String json = "{\"status\":200,\"data\":{\"category\":[{\"_id\":\"591934581fa13d21fc810484\",\"updatedAt\":\"2017-05-15T04:53:44.245Z\",\"createdAt\":\"2017-05-15T04:53:44.245Z\",\"name\":\"merchandise\",\"description\":\"description merchandise\",\"img_url\":\"\",\"__v\":0,\"products\":[{\"name\":\"KEEPCUP BREW\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x20160629142145a71f9be2da1d16cb0698aa5be5c65ffd.jpg.pagespeed.ic.RbSGqMrNkx.webp\",\"_id\":\"591934581fa13d21fc810489\",\"price\":[{\"name\":\"R\",\"amount\":17.99,\"_id\":\"591934581fa13d21fc81048a\"}]},{\"name\":\"KEEPCUP ORIGINAL BROWN\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x201606291417408aa31826b7f4aa6a3b773a09901609dc.jpg.pagespeed.ic.RJ5J1Wk7KH.webp\",\"_id\":\"591934581fa13d21fc810487\",\"price\":[{\"name\":\"R\",\"amount\":13.99,\"_id\":\"591934581fa13d21fc810488\"}]},{\"name\":\"KBACH MUG\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x20160629140427c65a90e00f7c359e4738718eb37682b3.jpg.pagespeed.ic.FzuE8PDpBP.webp\",\"_id\":\"591934581fa13d21fc810485\",\"price\":[{\"name\":\"R\",\"amount\":7.99,\"_id\":\"591934581fa13d21fc810486\"}]}],\"subcategory\":[]},{\"_id\":\"591934621fa13d21fc81048b\",\"updatedAt\":\"2017-05-15T04:53:54.712Z\",\"createdAt\":\"2017-05-15T04:53:54.712Z\",\"name\":\"drinks\",\"description\":\"description drinks\",\"img_url\":\"\",\"__v\":0,\"products\":[],\"subcategory\":[{\"name\":\"hot drinks\",\"description\":\"description hot drinks\",\"img_url\":\"\",\"_id\":\"591934621fa13d21fc810496\",\"products\":[{\"name\":\"ESPRESSO\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x20160627163843227c0a079f5889b769a36b1c867d4eff.jpg.pagespeed.ic.otZJ5LJNrg.webp\",\"_id\":\"591934621fa13d21fc810497\",\"price\":[{\"name\":\"R\",\"amount\":1.85,\"_id\":\"591934621fa13d21fc810498\"}]}]},{\"name\":\"cold drinks\",\"description\":\"description cold drinks\",\"img_url\":\"\",\"_id\":\"591934621fa13d21fc810491\",\"products\":[{\"name\":\"ICED VANILLA LATTE\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/201705040838179b9387d0500b06503644e869a881264f.jpg\",\"_id\":\"591934621fa13d21fc810492\",\"price\":[{\"name\":\"R\",\"amount\":2.85,\"_id\":\"591934621fa13d21fc810495\"},{\"name\":\"L\",\"amount\":3.65,\"_id\":\"591934621fa13d21fc810494\"},{\"name\":\"G\",\"amount\":4,\"_id\":\"591934621fa13d21fc810493\"}]}]},{\"name\":\"frappe\",\"description\":\"description frappe\",\"img_url\":\"\",\"_id\":\"591934621fa13d21fc81048c\",\"products\":[{\"name\":\"MOCHA CHIP CREME\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/20160607104926c819932dd6e15486336faa59b494cd9b.jpg\",\"_id\":\"591934621fa13d21fc81048d\",\"price\":[{\"name\":\"R\",\"amount\":2.95,\"_id\":\"591934621fa13d21fc810490\"},{\"name\":\"L\",\"amount\":3.95,\"_id\":\"591934621fa13d21fc81048f\"},{\"name\":\"G\",\"amount\":4.25,\"_id\":\"591934621fa13d21fc81048e\"}]}]}]},{\"_id\":\"5919346b1fa13d21fc810499\",\"updatedAt\":\"2017-05-15T04:54:03.484Z\",\"createdAt\":\"2017-05-15T04:54:03.484Z\",\"name\":\"foods\",\"description\":\"description foods\",\"img_url\":\"\",\"__v\":0,\"products\":[],\"subcategory\":[{\"name\":\"pasta\",\"description\":\"description pasta\",\"img_url\":\"\",\"_id\":\"5919346b1fa13d21fc8104a0\",\"products\":[{\"name\":\"CHICKEN CURRY SPAGHETTI\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x201606061557466716aae8acc74ccdfc783890ed53ffed.jpg.pagespeed.ic.q0SOB4VfRa.webp\",\"_id\":\"5919346b1fa13d21fc8104a1\",\"price\":[{\"name\":\"R\",\"amount\":4.25,\"_id\":\"5919346b1fa13d21fc8104a2\"}]}]},{\"name\":\"salad\",\"description\":\"description salad\",\"img_url\":\"\",\"_id\":\"5919346b1fa13d21fc81049d\",\"products\":[{\"name\":\"CAESAR SALAD\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/20160606160827ec82cc44fe9898343eadbe406dca7639.jpg\",\"_id\":\"5919346b1fa13d21fc81049e\",\"price\":[{\"name\":\"R\",\"amount\":3.95,\"_id\":\"5919346b1fa13d21fc81049f\"}]}]},{\"name\":\"all day cafe sandwiches\",\"description\":\"description all day cafe sandwiches\",\"img_url\":\"\",\"_id\":\"5919346b1fa13d21fc81049a\",\"products\":[{\"name\":\"CRISPY PARMESAN CHICKEN\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/201606061124021482b2c3c23cf94c830a2d97cdf87500.jpg\",\"_id\":\"5919346b1fa13d21fc81049b\",\"price\":[{\"name\":\"R\",\"amount\":4.15,\"_id\":\"5919346b1fa13d21fc81049c\"}]}]}]}]}}";

        try {

            JSONArray data = new JSONArray(new JSONObject(new JSONObject(json).getString("data")).getString("category"));

            for (int i=0; i<data.length(); i++){
                Log.d("result", data.getJSONObject(i).getString("name"));
                lisData.add(data.getJSONObject(i).getString("name"));

                if (new JSONArray(data.getJSONObject(i).getString("products")).length() < 1){

                    for (int j=0; j<new JSONArray(data.getJSONObject(i).getString("subcategory")).length(); j++){
                        JSONArray subcategory = new JSONArray(new JSONObject(String.valueOf(new JSONArray(data.getJSONObject(i).getString("subcategory")).getJSONObject(j))).getString("products"));

                        sub = new CateListModelInnerSub(new JSONObject(String.valueOf(new JSONArray(data.getJSONObject(i).getString("subcategory")).getJSONObject(j))).getString("name"));
                        lisData.add(sub);

                        for (int k=0; k<subcategory.length(); k++){
                            model = new CateListModel(subcategory.getJSONObject(k).getString("name"));
                            lisData.add(model);
                        }
                    }
                }
                else {
                    for (int j=0 ; j<new JSONArray(data.getJSONObject(i).getString("products")).length(); j++){
                        model = new CateListModel((new JSONArray(data.getJSONObject(i).getString("products")).getJSONObject(j).getString("name")));
                        lisData.add(model);
                    }
                }

            }

            Log.d("result", String.valueOf(lisData));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adp = new CateListAdp(getActivity(), lisData);
        lisCate.setAdapter(adp);
    }

    @Override
    public void onVolleyFinished(String response) {
        gettingFirstData = false;
    }

    @Override
    public void onVolleyError(String message) {

    }
}
