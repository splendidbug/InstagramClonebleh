package com.example.android.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class SignUp extends AppCompatActivity {

    TextView retrieve;
    Button button;
    String allBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        retrieve = findViewById(R.id.retrieve);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBoxers = ""; // allBoxers is a private string created to store the retrieved data such that it can easily be displayed

                ParseQuery<ParseObject> allQuery = ParseQuery.getQuery("Boxer");

                allQuery.whereGreaterThan("punchSpeed", 100);
                allQuery.setLimit(1);

                allQuery.findInBackground(new FindCallback<ParseObject>() { // to get 1 object we write getInBackground
                    // but to get all the objects, we type findInBackground.
                    // Since it's findInBackground, starts with small f, in the parentheses, we write new and capital F, it autocomplet
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null && objects.size()>0)
                    {
                        for(ParseObject objBoxer: objects){ // the loop runs for all of the objects, in each iteration, it's gonna name objects as parseObject
                            allBoxers = allBoxers + objBoxer.get("punchSpeed") + "\n";

                            //Here objBoxer is the object

                        }
                        Toast.makeText(SignUp.this, allBoxers, Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignUp.this, "Retrieve failure", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    public void helloWorldTapped(View view){

        ParseObject boxer = new ParseObject("Boxer"); // boxer is the name of the object of class Boxer [in the brackets in the string]
        boxer.put("punchSpeed", 700);   // .put sets the value of punchSpeed to 200
        boxer.put("punchPower", 200);
        boxer.put("kickSpeed", 550);
        boxer.put("kickPower", 950);
        boxer.saveInBackground(new SaveCallback() {  // here we used saveInBackground() instead of just save() because
            // save() saves through the main UI which can cause load hence lag in the main UI.
            // Whereas saveInBackground() saves by creating another thread, hence leaving the main UI empty

            @Override
            public void done(ParseException e) {    // here e is the variable which is going to have an error if anything goes wrong during the save
            if(e == null){     // this means that e has no errors
                Toast.makeText(SignUp.this, "boxer object is saved",Toast.LENGTH_LONG). show();
            }
            }
        });

//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("punchSpeed", 200);
//
//
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(SignUp.this, "kickBoxer object saved successfully", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }

    public void retrieveData(View view){ // retrieving data from the server

        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer"); // we create an object called parseQuery of class ParseQuery,
        // we get the query through the name of the class, here it's Boxer

        parseQuery.getInBackground("DDjoLKdCOK", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(object!=null && e == null)
                    retrieve.setText(object.get("punchSpeed")+ ""); // we do + "" to convert whatever was received to string

            }
        });


    }


}
