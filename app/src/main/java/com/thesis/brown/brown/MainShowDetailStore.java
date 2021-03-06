package com.thesis.brown.brown;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainShowDetailStore extends AppCompatActivity {


    //Location Data For Google Map
    double storeLocationLatitude;
    double storeLocationLongitude;
    String storeName;

    //View
    TextView textViewStoreName;
    TextView textViewStoreAddress;
    TextView textViewStorePhoneNumber;
    TextView textViewStoreOpenTime;
    ImageView imageViewStoreImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_detail_store);

        //store data
        Bundle storeDataBundle = getIntent().getBundleExtra("storeData");
        storeName = storeDataBundle.getString("name");
        String storeAddress = storeDataBundle.getString("address");
        String storePhoneNumber = storeDataBundle.getString("phone");
        String storeOpenTime = storeDataBundle.getString("time");
        int storeImage = storeDataBundle.getInt("storeImage");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar2));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(storeName);

        //location data
        storeLocationLatitude = storeDataBundle.getDouble("latitude");
        storeLocationLongitude = storeDataBundle.getDouble("longitude");


        //Views interraction..
        textViewStoreName = (TextView) findViewById(R.id.textViewStoreName);
        textViewStoreAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewStorePhoneNumber = (TextView) findViewById(R.id.textViewPhoneNumber);
        textViewStoreOpenTime = (TextView) findViewById(R.id.textViewOpenTime);
        imageViewStoreImage = (ImageView) findViewById(R.id.storeImageView);

        //set text for each TextView with the Receiver data from list store Fragment
        textViewStoreName.setText(storeName);
        textViewStoreAddress.setText(storeAddress);
        textViewStorePhoneNumber.setText(storePhoneNumber);
        textViewStoreOpenTime.setText(storeOpenTime);
        imageViewStoreImage.setImageResource(storeImage);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //When ViewOnTheMap Button was Clicked
    public void onButtonViewOnTheMapOnClick(View view) {

        Intent goToMapActivityIntent = new Intent(this,StoreLocationActivity.class);

        Bundle locationDataBundle = new Bundle();
        locationDataBundle.putDouble("latitude",storeLocationLatitude);
        locationDataBundle.putDouble("longitude",storeLocationLongitude);
        locationDataBundle.putString("name",storeName);

        goToMapActivityIntent.putExtra("locationData",locationDataBundle);

        startActivity(goToMapActivityIntent);

    }
}
