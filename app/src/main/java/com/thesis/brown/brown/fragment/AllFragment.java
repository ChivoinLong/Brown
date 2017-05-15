package com.thesis.brown.brown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {
    public static final String POSITION_KEY = "FragmentPositionKey";

    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList(50), createItemRes(50));
        recyclerView.setAdapter(recyclerAdapter);
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
            list.add("Item " + i);
        }
        return list;
    }
}