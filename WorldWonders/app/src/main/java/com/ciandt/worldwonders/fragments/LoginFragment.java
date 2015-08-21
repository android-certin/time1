package com.ciandt.worldwonders.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.activity.SignUpActivity;
import com.ciandt.worldwonders.activity.WorldWondersActivity;
import com.ciandt.worldwonders.model.User;

/**
 * Created by ffranca on 8/21/15.
 */
public class LoginFragment extends Fragment {
    private final int REQUEST_SIGNUP = 1;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("LoginActivity", "onCreate");

        Button btnSignUp = (Button)view.findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        Button btnLogin = (Button)view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WorldWondersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_CANCELED) {
            switch (requestCode)
            {
                case REQUEST_SIGNUP:
                    if (data.hasExtra("user")) {
                        EditText username = (EditText) view.findViewById(R.id.edit_username);

                        User user = (User) data.getSerializableExtra("user");

                        username.setText(user.getUsername());
                    }

                    break;
            }
        }
    }

}
