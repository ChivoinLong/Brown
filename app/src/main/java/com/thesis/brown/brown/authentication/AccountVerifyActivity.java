package com.thesis.brown.brown.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.my_support.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Obi-Voin Kenobi on 23-Jun-17.
 */

public class AccountVerifyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText etCode;
    private Button btnActivate;
    private Map<String, String> userMap;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verify);

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        etCode = (TextInputEditText) findViewById(R.id.etCode);
        btnActivate = (Button) findViewById(R.id.btnActivate);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Account Verification");

        btnActivate.setOnClickListener(this);
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnActivate.setEnabled(s.length() == 6);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String registerBy = getIntent().getStringExtra("register_by");
        String phone = getIntent().getStringExtra("phone");

        Toast.makeText(this, registerBy + "\n" + phone, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnActivate){
//            String registerBy = getIntent().getStringExtra("register_by");
//            String info = registerBy.equals("phone")?getIntent().getStringExtra("phone"):getIntent().getStringExtra("email");
            String registerBy = "phone";
            String info = "093385860";
            String code = etCode.getText().toString();

            dialog.show();
            userMap = new HashMap<>();
            userMap.put("register_by", registerBy);
            userMap.put(registerBy.equals("phone")?"phone":"email", info);
            userMap.put("verify_code", etCode.getText().toString());
            Log.e("TAG", "onClick: " + userMap);
            volleyPostUserData("https://brown-ordering-system.herokuapp.com/api/v1/users/account/verify/checkcode", userMap);
        }
    }

    private void prepareJson(String json) {
        JSONObject response = null;
        dialog.dismiss();
        try {
            response = new JSONObject(json);
            Toast.makeText(this, response.getString("message"), Toast.LENGTH_LONG).show();
            if (response.getString("status") == "200"){
                Intent intent = new Intent(getApplicationContext(), AccountSignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void volleyPostUserData(String url, final Map<String, String> params) {

        MyVolley.cancelOldPandingRequest();

        StringRequest stringRequest = new StringRequest(
                Request.Method.DEPRECATED_GET_OR_POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prepareJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Here is to Encoder your query string params because we cannot push special characters through URL

                String key = "", value = "";
                HashMap<String, String> maps = new HashMap<>();

                for (Map.Entry<String, String> entry : params.entrySet()) {

                    key = entry.getKey();

                    try {
                        value = URLEncoder.encode(entry.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    maps.put(key, value);
                }

                return maps;
            }
        };
        MyVolley.getMyInstance().addToRequestQueue(stringRequest);
    }

}
