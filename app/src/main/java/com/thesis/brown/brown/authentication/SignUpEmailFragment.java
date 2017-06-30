package com.thesis.brown.brown.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thesis.brown.brown.R;

/**
 * Created by Obi-Voin Kenobi on 13-Jun-17.
 */

public class SignUpEmailFragment extends Fragment {

    public static final String TAB_NAME = "EMAIL";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_email, container, false);
        return view;
    }
}
