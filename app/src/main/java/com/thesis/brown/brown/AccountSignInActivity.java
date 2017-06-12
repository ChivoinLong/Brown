package com.thesis.brown.brown;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class AccountSignInActivity extends AppCompatActivity implements View.OnClickListener {

    LoginButton loginButton;
    CallbackManager callbackManager;
    EditText etEmail, etPwd;
    Button btnSignIn;
    TextView tvForgetPwd, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_account_signin);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sign in");

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPwd = (EditText) findViewById(R.id.etPassword);
        tvForgetPwd = (TextView) findViewById(R.id.tvForgetPwd);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

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
        Toast.makeText(this, etEmail.getText().toString() + " " + etPwd.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
