package com.thesis.brown.brown.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.thesis.brown.brown.R;
import com.thesis.brown.brown.my_support.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AccountSignInActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextInputEditText etUser, etPwd;
    private Button btnSignIn;
    private TextView tvForgetPwd, tvSignUp;
    private Map<String, String> userMap;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_account_signin);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign in");

        etUser = (TextInputEditText) findViewById(R.id.etUser);
        etPwd = (TextInputEditText) findViewById(R.id.etPassword);
        tvForgetPwd = (TextView) findViewById(R.id.tvForgetPwd);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        btnSignIn.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("result", String.valueOf(loginResult.getAccessToken()));

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                                Log.v("LoginActivity", response.toString());
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("result", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("result", error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvSignUp) {
            startActivity(new Intent(this, AccountSignUpActivity.class));
        } else if (v.getId() == R.id.tvForgetPwd) {
            startActivity(new Intent(this, AccountForgotPasswordActivity.class));
        } else if (v.getId() == R.id.btnSignIn) {
            userMap = new HashMap<>();
            if (etUser.getText().toString().contains("@")) {
                userMap.put("register_by", "email");
                userMap.put("email", etUser.getText().toString());
            }
            else {
                userMap.put("register_by", "phone");
                userMap.put("phone", etUser.getText().toString());
            }
            userMap.put("password", etPwd.getText().toString());
            volleyPostUserData("https://brown-ordering-system.herokuapp.com/api/v1/users/login", userMap);
            dialog.show();
        }
//        Toast.makeText(this, etUser.getText().toString() + " " + etPwd.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private void volleyPostUserData(String url, final Map<String, String> params) {

        MyVolley.cancelOldPandingRequest();

        StringRequest stringRequest = new StringRequest(
                Request.Method.DEPRECATED_GET_OR_POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prepareData(response);
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

    private void prepareData(String json) {
        JSONObject response = null;
        try {
            dialog.dismiss();
            response = new JSONObject(json);
            Toast.makeText(this, response.getString("message"), Toast.LENGTH_LONG).show();
            if (response.getString("status").equals("200")) {
//                Intent verify = new Intent(getActivity(), AccountVerifyActivity.class);
//                verify.putExtra("register_by", "phone");
//                verify.putExtra("phone", phone.getText().toString());
//                startActivity(verify);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
