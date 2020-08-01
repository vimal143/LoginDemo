package com.example.login.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;

import java.util.Calendar;

public class Signup2nd extends AppCompatActivity {
    ImageView backBtn;
    TextView titleText;
    Button nextBtn;
    RadioGroup radioGroup;
    RadioButton selection;
    DatePicker date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2nd);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backBtn = findViewById(R.id.signBack);
        titleText = findViewById(R.id.SignUpTitle);
        nextBtn = findViewById(R.id.nextSignupActivity);
        radioGroup=findViewById(R.id.grp_radio);
        date=findViewById(R.id.date_picker);



    }

    public void callNext(View view) {
        if(!validateGender()|!validateAge()){
            return;
        }
        String FullName = getIntent().getStringExtra("name");
        String UserName =getIntent().getStringExtra("userName");
        String Emailid =getIntent().getStringExtra("email");
        String Password=getIntent().getStringExtra("password");

        selection=findViewById(radioGroup.getCheckedRadioButtonId());
        String _Gender=selection.getText().toString();
        int day=date.getDayOfMonth();
        int month=date.getMonth();
        int year= date.getYear();
        String date=day+":"+month+":"+year;


        Intent intent = new Intent(getApplicationContext(), Signup3rd.class);
        intent.putExtra("gender",_Gender);
        intent.putExtra("age",date);
        intent.putExtra("name",FullName);
        intent.putExtra("userName",UserName);
        intent.putExtra("email",Emailid);
        intent.putExtra("password",Password);
        startActivity(intent);

    }
    public void backtoSignupActivity(View view){
        finish();
    }

    private boolean validateGender(){
        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please Select Gender",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateAge(){
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);
        int userAge= date.getYear();
        int agevalid=currentYear-userAge;
        if(agevalid<14){
            Toast.makeText(this,"You are minor",Toast.LENGTH_SHORT).show();
            return false;

        }
        else{
            return true;
        }
    }
}