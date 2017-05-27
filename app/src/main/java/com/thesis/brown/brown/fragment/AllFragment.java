package com.thesis.brown.brown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thesis.brown.brown.ProductDetailActivity;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.RecyclerItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment implements RecyclerItemOnClickListener.OnItemClickListener {
    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String EXTRA_IMAGE = "com.thesis.brown.extraImage";
    public static final String EXTRA_TITLE = "com.thesis.brown.extraTitle";
    RecyclerView recyclerView;

    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), createItemList(50), createItemRes(50), false);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), recyclerView, this));

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
            list.add("Caramel Latte");
            list.add("Caramel Macchiato");
            list.add("Iced Vanilla Latte");
            list.add("Iced Mocha");
            list.add("Iced Green Tea Latte");
        }
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);

        switch (position){
            case 0:
                intent.putExtra(EXTRA_IMAGE, R.drawable.caramel_latte);
                break;
            case 1:
                intent.putExtra(EXTRA_IMAGE, R.drawable.caramel_macchiato);
                break;
            case 2:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_vanilla_latte);
                break;
            case 3:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_mocha);
                break;
            case 4:
                intent.putExtra(EXTRA_IMAGE, R.drawable.iced_green_tea_latte);
                break;
        }

        intent.putExtra(EXTRA_TITLE, ((TextView) view.findViewById(R.id.itemName)).getText());

        startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }







}
