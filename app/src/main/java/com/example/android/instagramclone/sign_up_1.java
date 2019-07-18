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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class sign_up_1 extends AppCompatActivity {

    Button next;
    EditText email_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_1);

        next = findViewById(R.id.next);

        email_signup = findViewById(R.id.email_signup);
        email_signup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    ParseUser appUser = new ParseUser();

                    appUser.setEmail(email_signup.getText().toString());

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                                Toast.makeText(sign_up_1.this, "Signup successful", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(sign_up_1.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(sign_up_1.this, nameAndPass.class);
                            startActivity(intent);


                        }
                    });
                }
                if(ParseUser.getCurrentUser()!=null)
                    transition();
                return false;

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();

                appUser.setEmail(email_signup.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            Toast.makeText(sign_up_1.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(sign_up_1.this, "Error bro -_-. Take a look at this: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(sign_up_1.this, nameAndPass.class);
                        startActivity(intent);


                    }
                });
            }
        });
    }
public void TappedLayout(View view)
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
        Intent intent = new Intent(sign_up_1.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
