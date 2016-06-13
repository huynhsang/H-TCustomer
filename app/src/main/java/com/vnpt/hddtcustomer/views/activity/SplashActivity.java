package com.vnpt.hddtcustomer.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vnpt.hddtcustomer.R;


public class SplashActivity extends AppCompatActivity {

    //Splash screen timer
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toLogin = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(toLogin);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
