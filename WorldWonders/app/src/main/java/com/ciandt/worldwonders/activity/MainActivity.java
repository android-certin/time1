package com.ciandt.worldwonders.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.base.BaseActivity;
import com.ciandt.worldwonders.fragments.LoginFragment;
import com.ciandt.worldwonders.fragments.WonderFragment;
import com.ciandt.worldwonders.model.User;

public class MainActivity extends BaseActivity {

    @NonNull
    private void replaceFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        replaceFragment(loginFragment, "login");

        loginFragment.setOnLoginListener(new LoginFragment.OnLoginListener() {

            @Override
            public void onLogin(User user) {
              replaceFragment(new WonderFragment(), "wonders");
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
