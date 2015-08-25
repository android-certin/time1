package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.base.BaseActivity;
import com.ciandt.worldwonders.model.User;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnSignUp = (Button)findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();

                EditText name = (EditText)findViewById(R.id.edit_name);
                EditText username = (EditText)findViewById(R.id.edit_username);
                EditText password = (EditText)findViewById(R.id.edit_password);

                user.setName(name.getText().toString());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                Intent intent = new Intent();

                intent.putExtra("user", user);

                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }
}
