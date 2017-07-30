package com.thesis.brown.brown;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thesis.brown.brown.fragment.AllFragment;
import com.thesis.brown.brown.realm_model.OrderedProduct;
import com.thesis.brown.brown.realm_model.Product;

import io.realm.Realm;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

//    DatabaseHandler db;
    private Realm realm;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fab;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

//        Slide slide = new Slide(Gravity.BOTTOM);
//        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
//        getWindow().setEnterTransition(slide);

//        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), AllFragment.EXTRA_ID);
//        supportPostponeEnterTransition();

//        ViewCompat.setTransitionName(findViewById(R.id.image), getIntent().getStringExtra(AllFragment.EXTRA_TRANSITION_NAME));

//        db = new DatabaseHandler(this);
//        selectedProduct = db.getProduct(getIntent().getStringExtra(AllFragment.EXTRA_ID));

        realm = Realm.getDefaultInstance();
        selectedProduct = realm.where(Product.class).equalTo("_id", getIntent().getStringExtra(AllFragment.EXTRA_ID)).findFirst();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(selectedProduct.getName());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(selectedProduct.getName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        final ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load(selectedProduct.getImg_url())
                .into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }
            @Override
            public void onError() { }
        });

//        TextView tvDesc = (TextView) findViewById(R.id.tvProductDesc);
//        tvDesc.setText(selectedProduct.get_desc());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
        int primary = getResources().getColor(R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getLightMutedColor(primaryDark));
        supportStartPostponedEnterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            OrderedProduct orderedProduct = realm.where(OrderedProduct.class).contains("id", selectedProduct.get_id()).findFirst();

            realm.beginTransaction();
            if (orderedProduct == null){
                OrderedProduct newOrder = realm.createObject(OrderedProduct.class);
                newOrder.setId(selectedProduct.get_id());
                newOrder.setSize("R");
                newOrder.setQuantity(1);
            }
            else {
                orderedProduct.setQuantity(orderedProduct.getQuantity() + 1);
            }
            realm.commitTransaction();

            Snackbar snackbar = Snackbar.make(collapsingToolbarLayout, selectedProduct.getName() + " has been added to your cart.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ProductDetailActivity.this, "Clear", Toast.LENGTH_SHORT).show();
                            OrderedProduct product = realm.where(OrderedProduct.class).contains("id", selectedProduct.get_id()).findFirst();

                            if (product != null) {
                                if (product.getQuantity() > 1){
                                    realm.beginTransaction();
                                    product.setQuantity(product.getQuantity() - 1);
                                    realm.commitTransaction();
                                }
                                else {
                                    realm.beginTransaction();
                                    product.deleteFromRealm();
                                    realm.commitTransaction();
                                }
                            }

                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }
}
