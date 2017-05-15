package com.thesis.brown.brown.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment {

    ListView lisCate;
    ArrayList<Object> lisData;
    CateListAdp adp;

    public static final String POSITION_KEY = "FragmentPositionKey";

    public static CategoryFragment newInstance(Bundle args) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList(50), null);
//        recyclerView.setAdapter(recyclerAdapter);
//
//        // remove a below line to show the recyclerView
//        recyclerView.setVisibility(View.GONE);

        // in Fragment we should use on onActivityCreated !
//        setControls();
        lisCate = (ListView)root.findViewById(R.id.lisCategoryFragment);
        setEvents();
//        startUp();
        lisData = new ArrayList<>();
        showListWithData();
        return root;
    }

    List<String> createItemList(int num){
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num; i++){
            list.add("Item " + i);
        }
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        setControls();
//        setEvents();
//        startUp();
    }

    void setControls (){

    }

    void setEvents (){

    }

    void startUp (){
        lisData = new ArrayList<>();
        showListWithData();


    }

    void showListWithData (){

        CateListModel model;

        lisData.add("Drink");
        for (int i=0; i<10; i++){
            model = new CateListModel("Drink : " + (i + 1));
            lisData.add(model);
        }

        lisData.add("Food");
        for (int i=0; i<10; i++){
            model = new CateListModel("Food : " + (i + 1));
            lisData.add(model);
        }

        lisData.add("Souvenir");
        for (int i=0; i<10; i++){
            model = new CateListModel("Souvenir : " + (i + 1));
            lisData.add(model);
        }

        lisData.add("Fuck");
        for (int i=0; i<10; i++){
            model = new CateListModel("Fuck : " + (i + 1));
            lisData.add(model);
        }

        adp = new CateListAdp(getActivity(), lisData);
        lisCate.setAdapter(adp);
    }
}
