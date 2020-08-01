package com.example.login.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Queue;

public class Login extends AppCompatActivity {
    Button createAc;
    TextInputLayout UserName, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        createAc = (Button) findViewById(R.id.createAccountBtn);
        UserName = findViewById(R.id.userNameforLogin);
        Password = findViewById(R.id.passwordforLogin);
    }

    public void createAccountRedirect(View view) {
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }

    public void backtoParentActivity(View view) {
        finish();
    }

    public void userLogin(final View view) {
        if (!validateFields()) {
            return;

        }
        final String _userName = UserName.getEditText().getText().toString().trim();
        final String _password = Password.getEditText().getText().toString().trim();


        Query checkUser= FirebaseDatabase.getInstance().getReference("UserDetails").orderByChild("userName").equalTo(_userName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    UserName.setError(null);
                    UserName.setErrorEnabled(false);


                    String systemPassword=snapshot.child(_userName).child("password").getValue(String.class);
                    if(systemPassword.equals(_password)){
                        Password.setError(null);
                        Password.setErrorEnabled(false);
                        Toast.makeText(Login.this, "You are Logged In SuccessFully", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        AlertDialog dialog=new AlertDialog.Builder(Login.this)
                                .setTitle(Html.fromHtml("<font color='#FF0000'>Error</font>"))
                                .setMessage(Html.fromHtml("<font color='#FF0000'>Password Didn't Match</font>"))
                                .setNegativeButton("Ok",null).show();
                    }
                }
                else{
                   // Toast.makeText(Login.this, "User Does not exist", Toast.LENGTH_SHORT).show();
                    AlertDialog dialog=new AlertDialog.Builder(Login.this)
                            .setTitle(Html.fromHtml("<font color='#FF0000'>Error</font>"))
                            .setMessage(Html.fromHtml("<font color='#FF0000'>No such user exist</font>"))
                            .setNegativeButton("Ok",null).show();


                    Button buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    buttonNegative.setTextColor(ContextCompat.getColor(Login.this, R.color.colorPrimary));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }

    private boolean validateFields() {
        String _userName = UserName.getEditText().getText().toString().trim();
        String _password = Password.getEditText().getText().toString().trim();

        if (_userName.isEmpty()) {
            UserName.setError("Field can not be Empty");
            UserName.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            Password.setError("Field can not be Empty");
            Password.requestFocus();
            return false;
        } else {
            UserName.setError(null);
            Password.setError(null);
            Password.setErrorEnabled(false);
            UserName.setErrorEnabled(false);
            return true;

        }
    }
}