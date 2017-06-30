package com.thesis.brown.brown;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.thesis.brown.brown.fragment.AllFragment;
import com.thesis.brown.brown.model.Product;
import com.thesis.brown.brown.my_support.DatabaseHandler;

public class ProductDetailActivity extends AppCompatActivity {

    DatabaseHandler db;
    private CollapsingToolbarLayout collapsingToolbarLayout;

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

        db = new DatabaseHandler(this);
        Product selectedProduct = db.getProduct(getIntent().getStringExtra(AllFragment.EXTRA_ID));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(selectedProduct.get_name());

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(selectedProduct.get_name());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

        final ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this)
                .load(selectedProduct.get_imgUrl())
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
}
