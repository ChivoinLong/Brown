package com.thesis.brown.brown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemName(10), createItemRes(10));
        recyclerView.setAdapter(recyclerAdapter);

        return root;
    }

    private List<Integer> createItemRes(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++){
            list.add(R.drawable.caramel_latte);
            list.add(R.drawable.caramel_macchiato);
            list.add(R.drawable.iced_vanilla_latte);
            list.add(R.drawable.iced_mocha);
            list.add(R.drawable.iced_green_tea_latte);
        }
        return list;
    }

    List<String> createItemName(int num){
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
}
