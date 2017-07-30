package com.thesis.brown.brown;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private double latitude;
    private double longitude;
    private String storeName;

    FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Map");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Get Location Data
        Bundle locationDataBundle = getIntent().getBundleExtra("locationData");
        latitude = locationDataBundle.getDouble("latitude");
        longitude = locationDataBundle.getDouble("longitude");
        storeName = locationDataBundle.getString("name");

        //float button interraction
        // Work : If user touch the button it will go back to the setted point of the brown location
        floatButton = (FloatingActionButton) findViewById(R.id.fab);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap!=null){
                    LatLng latLnglocation = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions()
                            .position(latLnglocation)
                            .title(storeName)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.cup_icon_map)));
                    float currentZoomLevel = mMap.getCameraPosition().zoom;
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLnglocation,currentZoomLevel));
                }
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Location and move the camera
        LatLng latLnglocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(latLnglocation)
                .title(storeName)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cup_icon_map)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLnglocation,17));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
