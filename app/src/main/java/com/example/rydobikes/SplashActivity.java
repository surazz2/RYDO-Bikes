package com.example.rydobikes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private Handler mWaitHandler = new Handler();
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "riderPrefs";

    Boolean rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        rememberMe = sharedPreferences.getBoolean("rememberMe", false);


        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (rememberMe) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 5000);

    }
}