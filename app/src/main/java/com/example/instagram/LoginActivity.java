package com.example.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegisterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            goMainActivity();
        } else {
            // show the signup or login screen


            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            btnRegisterMenu = findViewById(R.id.btnRegisterMenu);

            btnRegisterMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goRegisterActivity();

                }
            });


            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    login(username, password);

                }
            });



        }
    }


    private void goRegisterActivity() {

        Log.d(TAG,  "Navigating to Register Activity");

        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }


    private void login(String username, String password) {


        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if( e != null){
                    //TODO: better error handling
                    Log.e(TAG,  "Issue with login");
                    e.printStackTrace();
                    return;
                }

                //TODO: navigate to new activity if the user has signed properly
                goMainActivity();
            }
        });

    }



    private void goMainActivity() {
        Log.d(TAG,  "Navigating to Main Activity");

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
