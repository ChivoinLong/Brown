package com.thesis.brown.brown;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Obi-Voin Kenobi on 26-Mar-17.
 */

public class FeaturedFragment extends Fragment {
    public static final String POSITION_KEY = "FragmentPositionKey";

    public static FeaturedFragment newInstance(Bundle args) {
        FeaturedFragment fragment = new FeaturedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_featured, container, false);

//        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList(5));
//        recyclerView.setAdapter(recyclerAdapter);

        return root;
    }

    List<String> createItemList(int num){
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num; i++){
            list.add("Item " + i);
        }
        return list;
    }
}