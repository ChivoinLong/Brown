package com.thesis.brown.brown.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thesis.brown.brown.R;

/**
 * Created by Obi-Voin Kenobi on 13-Jun-17.
 */

public class SignUpPhoneNumberFragment extends Fragment {

    public static final String TAB_NAME = "PHONE NUMBER";
    private EditText name, phone, password, confirm_password;
    private Button btnSignUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_phonenumber, container, false);

        name = (EditText) view.findViewById(R.id.etName);
        phone = (EditText) view.findViewById(R.id.etPhone);
        password = (EditText) view.findViewById(R.id.etPassword);
        confirm_password = (EditText) view.findViewById(R.id.etConfirmPassword);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), name.getText() + "\n" + phone.getText() + "\n" + password.getText() + "\n" + confirm_password.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
