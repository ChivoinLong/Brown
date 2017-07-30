package com.thesis.brown.brown.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.thesis.brown.brown.ProductDetailActivity;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.RecyclerAdapter;
import com.thesis.brown.brown.RecyclerItemOnClickListener;
import com.thesis.brown.brown.realm_model.Product;

import io.realm.Realm;
import io.realm.RealmResults;

public class AllFragment extends Fragment implements RecyclerItemOnClickListener.OnItemClickListener {
    public static final String TAB_NAME = "ALL";
    public static final String POSITION_KEY = "FragmentPositionKey";
    public static final String EXTRA_ID = "com.thesis.brown.extraID";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Button btnRefresh;
    RealmResults<Product> products = null;
    Realm realm;

    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all, container, false);

        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar);
        btnRefresh = (Button) root.findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProducts();
                progressBar.setVisibility(View.VISIBLE);
                btnRefresh.setVisibility(View.GONE);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getContext(), recyclerView, this));
        loadProducts();

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra(EXTRA_ID, products.get(position).get_id());
//        intent.putExtra(EXTRA_TRANSITION_NAME, products.get(position).get_name());
//
//        ImageView itemIv = (ImageView) view.findViewById(R.id.itemImage);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), itemIv, products.get(position).get_name());

        startActivity(intent/*, options.toBundle()*/);
    }

    @Override
    public void onLongItemClick(View view, int position) {
    }

    private void loadProducts() {
        if (realm.isEmpty()){
            Log.e("AllFragment", "onResume: There's no data!!!");
//            volleyGetProductList("https://brown-ordering-system.herokuapp.com/api/v1/product/product", new HashMap<String, String>());
        }
        else {
            products = realm.where(Product.class).findAll();
            Log.e("AllFragment", "onResume: get data from realm database");
//        Log.e(TAG, "onResume: " + products.toString());
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), this.products, 1, false);
            recyclerView.setAdapter(recyclerAdapter);
            progressBar.setVisibility(View.GONE);

        }
    }





}
