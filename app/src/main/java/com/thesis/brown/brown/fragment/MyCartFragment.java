package com.thesis.brown.brown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.order.OrderActivity;
import com.thesis.brown.brown.realm_model.OrderedProduct;
import com.thesis.brown.brown.realm_model.Product;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Obi-Voin Kenobi on 29-Jun-17.
 */

public class MyCartFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Realm realm;
    RealmResults<Product> products = null;
    private RealmResults<OrderedProduct> orderedProducts = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        realm = Realm.getDefaultInstance();

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadOrderedProducts();

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        startActivity(intent);
    }

    private void loadOrderedProducts() {
        if (realm.isEmpty()){
            Log.e("AllFragment", "onResume: There's no data!!!");
//            volleyGetProductList("https://brown-ordering-system.herokuapp.com/api/v1/product/product", new HashMap<String, String>());
        }
        else {
            products = realm.where(Product.class).findAll();
            orderedProducts = realm.where(OrderedProduct.class).findAll();
            Log.e("AllFragment", "onResume: get data from realm database");
//        Log.e(TAG, "onResume: " + products.toString());
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), products, orderedProducts, 5);
            recyclerView.setAdapter(recyclerAdapter);

        }
    }
}
