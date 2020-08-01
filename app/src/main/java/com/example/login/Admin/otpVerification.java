package com.example.login.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otpVerification extends AppCompatActivity {
    Button code;
    PinView pinFromUser;
    String codeBySystem;
    String FullName,UserName,Emailid,Password,Gender,Age,MobileNo;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        code = findViewById(R.id.OTP_verify);
        pinFromUser = findViewById(R.id.pin_view);
        MobileNo = getIntent().getStringExtra("mobile");
        FullName = getIntent().getStringExtra("name");
        UserName =getIntent().getStringExtra("userName");
        Emailid =getIntent().getStringExtra("email");
        Password=getIntent().getStringExtra("password");
        Gender=getIntent().getStringExtra("gender");
        Age=getIntent().getStringExtra("age");
        sendVerificationCodeToUser(MobileNo);



    }

    private void sendVerificationCodeToUser(String mobileNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobileNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                  String code=phoneAuthCredential.getSmsCode();
                  if(code!=null){
                      pinFromUser.setText(code);

                      verifyCode(code);
                  }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(otpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem=s;
                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeNewUsersData();
                            Intent i=new Intent(getApplicationContext(),Login.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finishAffinity();
                            startActivity(i);

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(otpVerification.this, "Verifiaction Failed", Toast.LENGTH_SHORT).show();
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void storeNewUsersData() {
        FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
        DatabaseReference reference=rootNode.getReference("UserDetails");
        UserDetailsClass addUsers=new UserDetailsClass(FullName,UserName,Emailid,Password,Age,Gender,MobileNo);
        reference.child(UserName).setValue(addUsers);

    }

    public  void  verifyOTP(View view){
        String code=pinFromUser.getText().toString();
        if(!code.isEmpty()){
            verifyCode(code);
        }

    }
    public void restartWelcomeScreen(View view){

        Intent i=new Intent(getApplicationContext(),Welcome.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}