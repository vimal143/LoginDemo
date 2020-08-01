package com.example.login.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {
    ImageView backBtn;
    TextView titleText;
    Button nextBtn;
    TextInputLayout Name, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backBtn = findViewById(R.id.signBack);
        titleText = findViewById(R.id.SignUpTitle);
        nextBtn = findViewById(R.id.nextSignupActivity);


        //Getting Data
        Name = findViewById(R.id.fullName);
        username = findViewById(R.id.userName);
        email = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
    }

    public void callNextScreen(View view) {
        if (!validatefullName() | !validateuserName() | !validateEmail() | !validatePassword()) {
            return;
        }
        String FullName = Name.getEditText().getText().toString();
        String UserName = username.getEditText().getText().toString();
        String Emailid = email.getEditText().getText().toString();
        String Password = password.getEditText().getText().toString();


        Intent intent = new Intent(getApplicationContext(), Signup2nd.class);
        intent.putExtra("name",FullName);
        intent.putExtra("userName",UserName);
        intent.putExtra("email",Emailid);
        intent.putExtra("password",Password);
        startActivity(intent);


    }
    public void backtoWelcomeScreen(View view){
//        Intent intent=new Intent(getApplicationContext(),Welcome.class);
//        startActivity(intent);
        finish();
    }


    private boolean validatefullName() {
        String fullName = Name.getEditText().getText().toString().trim();
        if (fullName.isEmpty()) {
            Name.setError("Field can not be Empty");
            return false;
        } else {
            Name.setError(null);
            Name.setErrorEnabled(false);
            return true;


        }

    }

    private boolean validateuserName() {
        String userName = username.getEditText().getText().toString().trim();
        String CheckSpaces = "\\A\\w{4,20}\\z";
        if (userName.isEmpty()) {
            username.setError("Field can not be Empty");
            return false;
        } else if (userName.length() > 10) {
            username.setError("Username is too large");
            return false;

        } else if (!userName.matches(CheckSpaces)) {
            username.setError("White Spaces are not allowed");
            return false;

        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;


        }

    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String Checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field can not be Empty");
            return false;
        } else if (val.length() > 30) {
            email.setError("Email is too large");
            return false;

        } else if (!val.matches(Checkemail)) {
            email.setError("Invalid Email");
            return false;

        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;


        }

    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }


    }
}