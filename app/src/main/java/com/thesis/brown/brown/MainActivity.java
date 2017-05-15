package com.thesis.brown.brown;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.thesis.brown.brown.StoreList.MainListStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    LinearLayout linFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        setControls();
        setEvents();
        startUp();
    }

    void startUp(){

        String json = "[{\"_id\":\"591816db94fa1d13309646c6\",\"updatedAt\":\"2017-05-14T08:35:39.421Z\",\"createdAt\":\"2017-05-14T08:35:39.421Z\",\"__v\":0,\"products\":[],\"subcategory\":[{\"name\":\"pasta\",\"description\":\"description hot drinks\",\"img_url\":\"\",\"_id\":\"591816db94fa1d13309646cd\",\"products\":[{\"name\":\"CHICKEN CURRY SPAGHETTI\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x201606061557466716aae8acc74ccdfc783890ed53ffed.jpg.pagespeed.ic.q0SOB4VfRa.webp\",\"_id\":\"591816db94fa1d13309646ce\",\"price\":[{\"name\":\"R\",\"amount\":4.25,\"_id\":\"591816db94fa1d13309646cf\"}]}]},{\"name\":\"salad\",\"description\":\"description cold drinks\",\"img_url\":\"\",\"_id\":\"591816db94fa1d13309646ca\",\"products\":[{\"name\":\"CAESAR SALAD\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/20160606160827ec82cc44fe9898343eadbe406dca7639.jpg\",\"_id\":\"591816db94fa1d13309646cb\",\"price\":[{\"name\":\"R\",\"amount\":3.95,\"_id\":\"591816db94fa1d13309646cc\"}]}]},{\"name\":\"all day cafe sandwiches\",\"description\":\"description frappe\",\"img_url\":\"\",\"_id\":\"591816db94fa1d13309646c7\",\"products\":[{\"name\":\"CRISPY PARMESAN CHICKEN\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/201606061124021482b2c3c23cf94c830a2d97cdf87500.jpg\",\"_id\":\"591816db94fa1d13309646c8\",\"price\":[{\"name\":\"R\",\"amount\":4.15,\"_id\":\"591816db94fa1d13309646c9\"}]}]}],\"category\":[{\"name\":\"foods\",\"description\":\"description foods\",\"img_url\":\"\",\"_id\":\"591816db94fa1d13309646d0\"}]},{\"_id\":\"5918182e94fa1d13309646d1\",\"updatedAt\":\"2017-05-14T08:41:18.848Z\",\"createdAt\":\"2017-05-14T08:41:18.848Z\",\"__v\":0,\"products\":[],\"subcategory\":[{\"name\":\"hot drinks\",\"description\":\"description hot drinks\",\"img_url\":\"\",\"_id\":\"5918182e94fa1d13309646dc\",\"products\":[{\"name\":\"ESPRESSO\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/x20160627163843227c0a079f5889b769a36b1c867d4eff.jpg.pagespeed.ic.otZJ5LJNrg.webp\",\"_id\":\"5918182e94fa1d13309646dd\",\"price\":[{\"name\":\"R\",\"amount\":1.85,\"_id\":\"5918182e94fa1d13309646de\"}]}]},{\"name\":\"cold drinks\",\"description\":\"description cold drinks\",\"img_url\":\"\",\"_id\":\"5918182e94fa1d13309646d7\",\"products\":[{\"name\":\"ICED VANILLA LATTE\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/201705040838179b9387d0500b06503644e869a881264f.jpg\",\"_id\":\"5918182e94fa1d13309646d8\",\"price\":[{\"name\":\"R\",\"amount\":2.85,\"_id\":\"5918182e94fa1d13309646db\"},{\"name\":\"L\",\"amount\":3.65,\"_id\":\"5918182e94fa1d13309646da\"},{\"name\":\"G\",\"amount\":4,\"_id\":\"5918182e94fa1d13309646d9\"}]}]},{\"name\":\"frappe\",\"description\":\"description frappe\",\"img_url\":\"\",\"_id\":\"5918182e94fa1d13309646d2\",\"products\":[{\"name\":\"MOCHA CHIP CREME\",\"short_description\":\"\",\"description\":\"\",\"img_url\":\"https://www.browncoffee.com.kh/uploads/ximg/item_menus/20160607104926c819932dd6e15486336faa59b494cd9b.jpg\",\"_id\":\"5918182e94fa1d13309646d3\",\"price\":[{\"name\":\"R\",\"amount\":2.95,\"_id\":\"5918182e94fa1d13309646d6\"},{\"name\":\"L\",\"amount\":3.95,\"_id\":\"5918182e94fa1d13309646d5\"},{\"name\":\"G\",\"amount\":4.25,\"_id\":\"5918182e94fa1d13309646d4\"}]}]}],\"category\":[{\"name\":\"drinks\",\"description\":\"description drinks\",\"img_url\":\"\",\"_id\":\"5918182e94fa1d13309646df\"}]}]";

        try {
            JSONArray array = new JSONArray(json);
            Log.d("result", String.valueOf(array.length()));

            Log.d("result", String.valueOf(array.getJSONObject(0)));
            Log.d("result", String.valueOf(array.getJSONObject(1)));

            Log.d("result", String.valueOf(array.getJSONObject(0).getString("subcategory")));
            Log.d("result", String.valueOf(array.getJSONObject(1).getString("subcategory")));

//            Log.d("result", String.valueOf(new JSONObject(array.getJSONObject(0).getString("subcategory")).getJSONObject("")));
//            Log.d("result", String.valueOf(new JSONObject(array.getJSONObject(1).getString("subcategory"))));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void setEvents(){

    }

    void setControls(){
        linFirstRun = (LinearLayout)findViewById(R.id.linFirstRun);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        android.app.FragmentManager manager = getFragmentManager();

        switch (item.getItemId()){
            case R.id.nav_menu:
                startActivity(new Intent(this, MenuActivity.class));
                break;

            case R.id.nav_shop_location:
                setTitle("Shop Location");
                linFirstRun.setVisibility(View.GONE);
                manager.beginTransaction().replace(R.id.main_content,new MainListStore()).commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}
