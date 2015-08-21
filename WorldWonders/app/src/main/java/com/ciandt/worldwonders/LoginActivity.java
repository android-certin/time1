package com.ciandt.worldwonders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.model.User;

/**
 * Created by ffranca on 8/20/15.
 */
public class LoginActivity extends AppCompatActivity {
    private final int REQUEST_SIGNUP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("LoginActivity", "onCreate");

        Button btnSignUp = (Button)findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode)
            {
                case REQUEST_SIGNUP:
                    if (data.hasExtra("user")) {
                        EditText username = (EditText) findViewById(R.id.edit_username);

                        User user = (User) data.getSerializableExtra("user");

                        username.setText(user.getUsername());
                    }

                    break;
            }
        }
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
