package com.ciandt.worldwonders.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.fragments.LoginFragment;
import com.ciandt.worldwonders.fragments.HighlightFragment;
import com.ciandt.worldwonders.model.User;

public class MainActivity extends AppCompatActivity{

    @NonNull
    private Fragment replaceFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();

        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = (LoginFragment) replaceFragment(new LoginFragment(), "login");

        loginFragment.setOnLoginListener(new LoginFragment.OnLoginListener() {

            @Override
            public void onLogin(User user) {
                replaceFragment(new HighlightFragment(), "wonders");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LoginActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LoginActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LoginActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LoginActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LoginActivity", "onDestroy");
    }


}
