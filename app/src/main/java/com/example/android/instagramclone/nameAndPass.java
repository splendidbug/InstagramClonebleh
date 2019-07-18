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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class nameAndPass extends AppCompatActivity {
    Button signup;
    EditText username_signup, password_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_pass);
        signup = findViewById(R.id.signup);
        username_signup = findViewById(R.id.username_signup);
        password_signup = findViewById(R.id.password_signup);


        password_signup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN) {

                    ParseUser appUser = new ParseUser();
                    appUser.setUsername(username_signup.getText().toString());
                    appUser.setPassword(password_signup.getText().toString());


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                                Toast.makeText(nameAndPass.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(nameAndPass.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                    return false;
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(username_signup.getText().toString());
                appUser.setPassword(password_signup.getText().toString());


                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) {
                            Toast.makeText(nameAndPass.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            transition();
                        }
                        else
                            Toast.makeText(nameAndPass.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if(ParseUser.getCurrentUser()!=null)
            transition();

    }
    public  void Tapped_Layout(View view)
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
        Intent intent = new Intent(nameAndPass.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
