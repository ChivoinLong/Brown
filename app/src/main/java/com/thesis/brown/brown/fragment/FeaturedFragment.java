package com.thesis.brown.brown.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thesis.brown.brown.R;

public class FeaturedFragment extends Fragment {

    public static final String TAB_NAME = "FEATURED";
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
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), createItem(10), true);
//        recyclerView.setAdapter(recyclerAdapter);

        return root;
    }

}
