package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    EditText userNameSignUp, passwordSignUp, userNameLogin, passwordLogin;
    Button signUp, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        userNameSignUp = findViewById(R.id.userNameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        userNameLogin = findViewById(R.id.userNameLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        signUp = findViewById(R.id.signUp);
        login = findViewById(R.id.login);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(userNameSignUp.getText().toString());
                appUser.setPassword(passwordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            Toast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(SignUpLoginActivity.this, "User has already signed up, please login", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(userNameLogin.getText().toString(), passwordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e==null)
                        {
                            Toast.makeText(SignUpLoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(SignUpLoginActivity.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
