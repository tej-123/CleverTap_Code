package com.example.clevertap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edt_name, edt_email;
    Button btn_login, btn_Test;
    String name , email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);

        edt_name =  findViewById(R.id.name);
        edt_email =  findViewById(R.id.email);
        btn_login =  findViewById(R.id.btn_login);
        btn_Test =  findViewById(R.id.btn_Test);

        //clevertapDefaultInstance.pushEvent("Product Viewed"); // for launch application


        btn_login.setOnClickListener(v->{

            name = edt_name.getText().toString();
            email = edt_email.getText().toString();

            Log.i("CleverTap", "onCreate: Name = " + name);
            Log.i("CleverTap", "onCreate: Email = " + email);

            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Name", name);    // String
            profileUpdate.put("Identity", 61026032);   // String or number
            profileUpdate.put("Email", email);  // Email address of the user

            // optional fields. controls whether the user will be sent email, push etc.
            profileUpdate.put("MSG-email", true);        // email notifications
            profileUpdate.put("MSG-push", true);         // push notifications

            assert clevertapDefaultInstance != null;
            clevertapDefaultInstance.onUserLogin(profileUpdate);


            CleverTapAPI.setDebugLevel(61026032);

        });

        // for Custom Events
        btn_Test.setOnClickListener(v->{

            clevertapDefaultInstance.pushEvent("Product Viewed");  /// event without properties


//            // event with properties
//            HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
//            prodViewedAction.put("Product Name", "Casio Chronograph Watch");
//            prodViewedAction.put("Category", "Mens Accessories");
//            prodViewedAction.put("Price", 59.99);
//            prodViewedAction.put("Date", new java.util.Date());
//
//            clevertapDefaultInstance.pushEvent("Product Viewed", prodViewedAction);


        });
    }
}
