package com.thesis.brown.brown.order;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thesis.brown.brown.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Obi-Voin Kenobi on 09-Jul-17.
 */

public class OrderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Information");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                findViewById(R.id.spnStore).setVisibility(checkedId == R.id.rbtnTakeAway ? View.VISIBLE : View.GONE);
                findViewById(R.id.etTime).setVisibility(checkedId == R.id.rbtnTakeAway ? View.VISIBLE : View.GONE);

                findViewById(R.id.spnAddress).setVisibility(checkedId == R.id.rbtnDelivery ? View.VISIBLE : View.GONE);
            }
        });

        Spinner spnAddress = (Spinner) findViewById(R.id.spnAddress);

        ArrayList addressArray = new ArrayList();
        addressArray.add("Home");
        addressArray.add("Work");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item,
                android.R.id.text1,
                addressArray);

        spnAddress.setAdapter(adapter);

        Spinner spnStore = (Spinner) findViewById(R.id.spnStore);

        ArrayList storeArray = new ArrayList();
        storeArray.add("Brown 51");
        storeArray.add("Brown IFL");

        adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item,
                android.R.id.text1,
                storeArray);

        spnStore.setAdapter(adapter);


        final TextView etTime = (TextView) findViewById(R.id.etTime);
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OrderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
