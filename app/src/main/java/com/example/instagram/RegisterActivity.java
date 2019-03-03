package com.example.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmailAddress;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String emailAddress = etEmailAddress.getText().toString();
                register(username, emailAddress, password);

            }
        });



    }

    private void register(String username, String emailAddress, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
         // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(emailAddress);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    goMainActivity();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "Issue with Registration");
                    e.printStackTrace();
                    return;
                }
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
