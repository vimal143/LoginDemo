package com.example.login.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.login.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Signup3rd extends AppCompatActivity {
    Button netscape;
    TextInputLayout userPhone;
    CountryCodePicker countryCodePicker;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3rd);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        netscape = findViewById(R.id.otpActivity);
        userPhone = findViewById(R.id.userPhoneNumber);
        countryCodePicker = findViewById(R.id.country);


    }
    public void otpScreen(View v){




        String FullName = getIntent().getStringExtra("name");
        String UserName =getIntent().getStringExtra("userName");
        String Emailid =getIntent().getStringExtra("email");
        String Password=getIntent().getStringExtra("password");
        String _Gender=getIntent().getStringExtra("gender");
        String age=getIntent().getStringExtra("age");

        String enteredNo=userPhone.getEditText().getText().toString().trim();
        String mobileNo="+"+countryCodePicker.getFullNumber()+enteredNo;





        Intent intent=new Intent(getApplicationContext(), otpVerification.class);
        intent.putExtra("gender",_Gender);
        intent.putExtra("age",age);
        intent.putExtra("name",FullName);
        intent.putExtra("userName",UserName);
        intent.putExtra("email",Emailid);
        intent.putExtra("password",Password);
        intent.putExtra("mobile",mobileNo);
        startActivity(intent);


    }
    public void backtoSignup2Activity(View view){
        finish();
    }
}
