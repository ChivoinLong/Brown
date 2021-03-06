package com.thesis.brown.brown.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Obi-Voin Kenobi on 13-Jun-17.
 */

public class SignUpPhoneNumberFragment extends Fragment {

    public static final String TAB_NAME = "PHONE NUMBER";
    private TextInputEditText name, phone, password, confirm_password;
    private Button btnSignUp;
    private Map<String, String> userMap;
    private ProgressDialog dialog;
    private boolean isAccountCreated = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_phonenumber, container, false);

        dialog = new ProgressDialog(getContext()); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        name = (TextInputEditText) view.findViewById(R.id.etName);
        phone = (TextInputEditText) view.findViewById(R.id.etPhone);
        password = (TextInputEditText) view.findViewById(R.id.etPassword);
        confirm_password = (TextInputEditText) view.findViewById(R.id.etConfirmPassword);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userMap = new HashMap<>();
                userMap.put("register_by", "phone");
                userMap.put("name", name.getText().toString());
                userMap.put("phone", phone.getText().toString());
                userMap.put("password", password.getText().toString());
                userMap.put("confirm_password", confirm_password.getText().toString());
                volleyPostUserData("https://brown-ordering-system.herokuapp.com/api/v1/users/signup", userMap);
                dialog.show();
            }
        });

        return view;
    }

    private void prepareJson(String json) {
        JSONObject response = null;
        dialog.dismiss();
        if (!isAccountCreated) {
            try {
                response = new JSONObject(json);
                Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_LONG).show();
                if (response.getString("status").equals("200")) {
                    isAccountCreated = true;

                    userMap = new HashMap<>();
                    userMap.put("register_by", "phone");
                    userMap.put("phone", phone.getText().toString());
                    volleyPostUserData("https://brown-ordering-system.herokuapp.com/api/v1/users/account/verify/sendcode", userMap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                response = new JSONObject(json);
                Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_LONG).show();
                if (response.getString("status").equals("200")) {
                    Intent verify = new Intent(getActivity(), AccountVerifyActivity.class);
                    verify.putExtra("register_by", "phone");
                    verify.putExtra("phone", phone.getText().toString());
                    startActivity(verify);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                        Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
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
