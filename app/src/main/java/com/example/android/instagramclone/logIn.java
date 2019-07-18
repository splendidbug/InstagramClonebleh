package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class logIn extends AppCompatActivity {

    EditText username_login, password_login;
    Button login;
    TextView tvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);

        password_login.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN) {
                    ParseUser appUser = new ParseUser();
                    ParseUser.logInInBackground(username_login.getText().toString(), password_login.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null)
                                Toast.makeText(logIn.this, "Log in successful", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(logIn.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return false;
            }
        });
        login = findViewById(R.id.login);
        tvSignUp = findViewById(R.id.tvSignUp);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                ParseUser.logInInBackground(username_login.getText().toString(), password_login.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(logIn.this, "Log in successful", Toast.LENGTH_SHORT).show();
                            transition();
                        }
                        else
                            Toast.makeText(logIn.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(logIn.this, nameAndPass.class);
                startActivity(intent);
            }
        });

        if(ParseUser.getCurrentUser()!=null)
            transition();


}
public void LayoutTapped(View view)
{
    try{
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

    public void transition(){
        Intent intent = new Intent(logIn.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
