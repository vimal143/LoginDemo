package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.Admin.Welcome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View admin = findViewById(R.id.userIcon);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                startActivity(intent);
            }
        });
        blink();

    }

    private void blink() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 400;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = (TextView) findViewById(R.id.click);
                        ImageView img=(ImageView)findViewById(R.id.arrow);
                        if (txt.getVisibility() == View.VISIBLE && img.getVisibility()==View.VISIBLE) {
                            txt.setVisibility(View.INVISIBLE);
                            img.setVisibility(View.INVISIBLE);
                        } else {
                            txt.setVisibility(View.VISIBLE);
                            img.setVisibility(View.VISIBLE);

                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}