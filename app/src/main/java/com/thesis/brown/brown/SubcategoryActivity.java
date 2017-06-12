package com.thesis.brown.brown;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.thesis.brown.brown.model.Category;
import com.thesis.brown.brown.model.Product;
import com.thesis.brown.brown.my_support.DatabaseHandler;

import java.util.ArrayList;

public class SubcategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<Product> products = null;
    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        db = new DatabaseHandler(this);

        Category selectedCategory = db.getCategory(getIntent().getStringExtra("CATEGORY_ID"));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(selectedCategory.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (db.getProductCount() == 0) {
//            loadProducts();
        } else {
//            products = db.getAllProducts();
            Log.d("123", "onResume: " + products.size());
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, this.products, false);
            recyclerView.setAdapter(recyclerAdapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
